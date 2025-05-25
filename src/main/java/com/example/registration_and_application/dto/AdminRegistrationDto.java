package com.example.registration_and_application.dto;

import com.example.registration_and_application.enums.Role;
import lombok.Data;

@Data
public class AdminRegistrationDto {
    private String email;
    private Role role;
    private String name;
    private String phoneNumber;
    private int age;
}