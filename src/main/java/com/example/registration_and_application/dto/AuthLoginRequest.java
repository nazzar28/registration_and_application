package com.example.registration_and_application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthLoginRequest {
    private String email;
    private String password;
}