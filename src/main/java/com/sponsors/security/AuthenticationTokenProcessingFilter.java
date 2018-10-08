package com.sponsors.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sponsors.dto.AccessTokenModel;
import com.sponsors.dto.CustomUserDetail;
import com.sponsors.security.tokenStore.TokenStoreService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

public class AuthenticationTokenProcessingFilter extends GenericFilterBean {

	private final TokenStoreService tokenStoreService;

	private final String principal;
	private final List<GrantedAuthority> authorities;

	public AuthenticationTokenProcessingFilter(TokenStoreService tokenStoreService, String authority,
			String principal) {
		this.tokenStoreService = tokenStoreService;
		this.principal = principal;
		this.authorities = AuthorityUtils.createAuthorityList(authority);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (!(request instanceof HttpServletRequest)) {
			throw new RuntimeException("Expecting a HTTP request");
		}

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String authToken = null;
		if (StringUtils.isNotBlank(httpRequest.getHeader("Authorization"))) {
			authToken = httpRequest.getHeader("Authorization");
		}
		AccessTokenModel objUserDetails = null;
		UserDetails userDetails = new CustomUserDetail(this.principal, "", this.authorities, null);
		if (StringUtils.isNotBlank(authToken)) {
			try {
				objUserDetails = this.tokenStoreService.readAccessToken(authToken);
			} catch (Exception exception) {
				logger.error("error while reading access token", exception);
			}
			if (objUserDetails != null) {
				List<String> userRoles = objUserDetails.getUserRoles();
				List<GrantedAuthority> objAuthorities = new ArrayList<>();
				for (String role : userRoles) {
					SimpleGrantedAuthority objAuthority = new SimpleGrantedAuthority(role);
					objAuthorities.add(objAuthority);
				}
				userDetails = new CustomUserDetail(objUserDetails.getUsername(), objUserDetails.getUser().getPassword(),
						true, true, true, true, objAuthorities, objUserDetails.getUser());
			}
		} else {
			HttpSession session = httpRequest.getSession(false);
			if (session != null && session.getAttribute("userdetail") != null) {
				userDetails = (UserDetails) session.getAttribute("userdetail");
			}
		}
		setAuthentication(userDetails, httpRequest);

		HttpServletResponse httpResponse = (HttpServletResponse) response;
		// Allow cross domain
		httpResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
		httpResponse.setHeader("Access-Control-Max-Age", "3600");
		// Allow set custom header token
		httpResponse.setHeader("Access-Control-Allow-Headers",
				"Content-Type, x-requested-with, X-Custom-Header, Authorization");

		chain.doFilter(request, response);

	}

	private void setAuthentication(UserDetails objUserDetails, HttpServletRequest request) {
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(objUserDetails,
				null, objUserDetails.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		SecurityContextHolder.getContext().getAuthentication();
	}

}
