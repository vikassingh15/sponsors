package com.sponsors.security.tokenStore;

import com.sponsors.dto.AccessTokenContainer;
import com.sponsors.dto.AccessTokenModel;
import com.sponsors.model.User;

public interface TokenStoreService {

	public AccessTokenModel readAccessToken(String accessToken);

	public AccessTokenContainer generateAccessToken(AccessTokenModel accessTokenModel);

	public void setExpTimeInMinute(long expTimeInMinute);

	public void removeToken(String accessToken);

	public boolean isTokenExist(String accessToken);

	public void updateUser(User user, String token);

	public boolean updateToken(String token, AccessTokenModel accessTokenModel);


}
