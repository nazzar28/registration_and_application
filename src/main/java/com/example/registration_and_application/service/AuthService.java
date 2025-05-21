package com.example.registration_and_application.service;

import com.example.registration_and_application.dto.UserRegisterRequest;

public interface AuthService {
    void register(UserRegisterRequest userRegisterRequest);

//    void password_reset(String email);
}