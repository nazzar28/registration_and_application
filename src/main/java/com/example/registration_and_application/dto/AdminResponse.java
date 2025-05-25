package com.example.registration_and_application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminResponse {
    private Long id;

    private String name;
    private String phoneNumber;
    private int age;
    private String email;
}