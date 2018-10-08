package com.sponsors.service;

import com.sponsors.dto.AccessTokenModel;
import com.sponsors.dto.UserLoginDto;
import com.sponsors.model.User;

public interface UserService {
    AccessTokenModel getUserDetailForLogin(UserLoginDto loginDto);

    User getUserByEmail(String email);
}
