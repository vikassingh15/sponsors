package com.sponsors.dto;

import com.sponsors.model.User;

import java.io.Serializable;
import java.util.List;

public class AccessTokenModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private boolean rememberMe;
	private List<String> userRoles;
	private User user;

	public AccessTokenModel() {
	}

	public AccessTokenModel(String username, String password, List<String> userRoles, User user, boolean rememberMe) {
		this.username = username;
		this.password = password;
		this.userRoles = userRoles;
		this.user = user;
		this.rememberMe = rememberMe;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<String> userRoles) {
		this.userRoles = userRoles;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

}