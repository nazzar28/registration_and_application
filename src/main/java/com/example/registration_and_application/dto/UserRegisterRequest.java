package com.example.registration_and_application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequest {

    private String email;
    private String name;
    private String password;
    private Integer age;
}