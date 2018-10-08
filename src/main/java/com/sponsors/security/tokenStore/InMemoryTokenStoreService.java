package com.sponsors.security.tokenStore;

import com.sponsors.dto.AccessTokenContainer;
import com.sponsors.dto.AccessTokenModel;
import com.sponsors.util.DateUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryTokenStoreService extends AbstractTokenStore implements TokenStoreService {

	private ConcurrentHashMap<String, AccessTokenModel> token = new ConcurrentHashMap<>();

	public InMemoryTokenStoreService() {
	}

	public void removeToken(String accessToken) {
		if (this.token.containsKey(accessToken)) {
			this.token.remove(accessToken);
		}
	}

	public AccessTokenModel readAccessToken(String accessToken) {
		AccessTokenModel accessTokenModel = null;
		if (validateToken(accessToken)) {
			accessTokenModel = this.token.get(accessToken);
		}
		return accessTokenModel;
	}

	public AccessTokenContainer generateAccessToken(AccessTokenModel accessTokenModel) {
		String accessToken = null;
		String formatedDate = null;
		accessToken = generateToken(accessTokenModel.getUsername(), accessTokenModel.isRememberMe());
		if (null != accessToken) {
			Date expireDate = getExpirationDateFromToken(accessToken);
			formatedDate = DateUtils.getDateString(expireDate, "yyyy-MM-dd HH:mm:ss");
			this.token.put(accessToken, accessTokenModel);
			return new AccessTokenContainer(accessToken, formatedDate);
		} else {
			throw new RuntimeException("Error in generation of access token");
		}
	}

	@Override
	public void setExpTimeInMinute(long expTimeInMinute) {
		this.expirationToken = expTimeInMinute;
	}

	@Override
	public boolean isTokenExist(String accessToken) {
		return this.token.containsKey(accessToken);
	}

	@Override
	public boolean updateToken(String token, AccessTokenModel accessTokenModel) {
		this.token.put(token, accessTokenModel);
		return true;
	}

	@Override
	public AccessTokenModel getAccessToken(String accessToken) {
		return (AccessTokenModel) this.token.get(accessToken);
	}
}
