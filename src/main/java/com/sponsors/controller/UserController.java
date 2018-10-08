package com.sponsors.controller;

import com.sponsors.dto.AccessTokenContainer;
import com.sponsors.dto.CandidateDto;
import com.sponsors.dto.UserLoginDto;
import com.sponsors.security.tokenStore.TokenStoreService;
import com.sponsors.service.CandidateService;
import com.sponsors.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/u")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenStoreService tokenStoreService;

    @PostMapping("/login")
    public ResponseEntity<AccessTokenContainer> login(@RequestBody @Validated UserLoginDto loginDto) {
        return new ResponseEntity<>(tokenStoreService.generateAccessToken(this.userService.getUserDetailForLogin(loginDto)), HttpStatus.OK);
    }
}
