package com.app.e_commerce.user.service.impl;

import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.app.e_commerce.user.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Override
    public Map<String, String> getPrincipalAuth() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return Map.of("Hello World", jwt.getSubject());
    }
    
}
