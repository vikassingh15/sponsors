package com.sponsors.security.tokenStore;

import com.sponsors.dto.AccessTokenModel;
import com.sponsors.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTokenStore implements TokenStoreService {

	private static final Logger log = LoggerFactory.getLogger(AbstractTokenStore.class);

	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_AUDIENCE = "audience";
	static final String CLAIM_KEY_CREATED = "created";
	static final String CLAIM_KEY_REMEMBER = "remember";
	static final String CLAIM_EXP_DATE = "expdate";

	private static final String AUDIENCE_WEB = "web";

	private String secret = "demo";

	protected long expirationToken = 24 * 3600 * 1000;

	public void removeClaim(String token) {
		Claims claims = getClaimsFromToken(token);
		if (claims != null) {
			claims.put(CLAIM_KEY_USERNAME, null);
			claims.clear();
		}
	}

	private String generateToken(Map<String, Object> claims, boolean rememberme) {
		claims.put(CLAIM_EXP_DATE, generateExpirationDate(rememberme));
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public Date generateExpirationDate(boolean rememberme) {
		if (rememberme) {
			return new Date(System.currentTimeMillis() + Integer.MAX_VALUE);
		} else {
			return new Date(System.currentTimeMillis() + expirationToken);
		}
	}

	public abstract AccessTokenModel getAccessToken(String accessToken);


	public String generateToken(String userName, boolean rememberme) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, userName);
		claims.put(CLAIM_KEY_AUDIENCE, AUDIENCE_WEB);
		claims.put(CLAIM_KEY_CREATED, new Date());
		claims.put(CLAIM_KEY_REMEMBER, rememberme);
		return generateToken(claims, rememberme);
	}

	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	public Boolean validateToken(String token) {
		return (isTokenExist(token) && !isTokenExpired(token));
	}

	protected Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		if (expiration != null) {
			return expiration != null && expiration.before(new Date());
		} else {
			removeToken(token);
			return true;
		}
	}

	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = getClaimsFromToken(token);
			expiration = new Date((long) claims.get(CLAIM_EXP_DATE));
			if (new Date().after(expiration)) {
				expiration = null;
			}
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}


	@Override
	public void updateUser(User user, String token) {
		AccessTokenModel accessTokenModel = this.readAccessToken(token);
		accessTokenModel.setUser(user);
		this.updateToken(token, accessTokenModel);
	}

}
