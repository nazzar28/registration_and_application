package com.example.registration_and_application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthLoginResponse {
    private Long id;
    private String email;
    private String name;
    private String token;
}