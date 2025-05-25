package com.example.registration_and_application.service;

import com.example.registration_and_application.dto.AuthLoginRequest;
import com.example.registration_and_application.dto.AuthLoginResponse;
import com.example.registration_and_application.dto.UserRegisterRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    void register(UserRegisterRequest userRegisterRequest);

    AuthLoginResponse login(AuthLoginRequest authLoginRequest);

    public UserDetails loadUserByUsername(String email);

//    void password_reset(String email);
}