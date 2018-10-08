package com.sponsors.service.impl;

import com.sponsors.dto.AccessTokenModel;
import com.sponsors.dto.UserLoginDto;
import com.sponsors.exception.NotAcceptableException;
import com.sponsors.exception.NotFoundException;
import com.sponsors.model.User;
import com.sponsors.repository.UserRepository;
import com.sponsors.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.sponsors.exception.NotAcceptableException.NotAcceptable.PASSWORD_NOT_MATCHES;
import static com.sponsors.exception.NotFoundException.NotFound.USER_NOT_FOUND;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AccessTokenModel getUserDetailForLogin(UserLoginDto loginDto) {
        User user = getUserByEmail(loginDto.getEmail());

        if(!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())){
            throw new NotAcceptableException(PASSWORD_NOT_MATCHES);
        }

        List<String> userRoles = user.getUserRoles().stream()
                .map(userRole -> userRole.getRole().getName())
                .collect(Collectors.toList());

        if (null != userRoles && !userRoles.isEmpty()) {
            return new AccessTokenModel(loginDto.getEmail(), loginDto.getPassword(), userRoles, user, false);
        } else {
            throw  new NotFoundException(USER_NOT_FOUND);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                    .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
    }
}
