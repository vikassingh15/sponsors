package com.sponsors;

import com.sponsors.security.AuthenticationTokenProcessingFilter;
import com.sponsors.security.CustomUserDetailsService;
import com.sponsors.security.tokenStore.TokenStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = CustomUserDetailsService.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Qualifier("customUserDetailsService")
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private TokenStoreService tokenStoreService;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/","/u/login")
				.permitAll();

		http.authorizeRequests()
				.antMatchers("/api/**")
				.hasAnyAuthority("SPONSOR_MANAGERS","SPONSOR_OFFICERS","SPONSOR_RECIPIENT")
				.anyRequest()
				.authenticated();

		http.csrf().disable().addFilterBefore(authenticationTokenProcessingFilter(), FilterSecurityInterceptor.class);
		http.headers().frameOptions().disable();
	}

	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public AuthenticationTokenProcessingFilter authenticationTokenProcessingFilter() {
		return new AuthenticationTokenProcessingFilter(tokenStoreService, "ANONYMOUS", "anonymousUser");
	}
}
