package com.sponsors.util;

import com.sponsors.dto.CustomUserDetail;
import com.sponsors.model.User;
import com.sponsors.security.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {

	private static final Logger log = LoggerFactory.getLogger(UserUtils.class);

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	public User getUser(Authentication auth) {
		User user = null;
		if (auth != null) {
			CustomUserDetail customUserDetail = (CustomUserDetail) auth.getPrincipal();
			if (customUserDetail != null && customUserDetail.getUser() != null) {
				user = customUserDetail.getUser();
			}
		}
		return user;
	}

}
