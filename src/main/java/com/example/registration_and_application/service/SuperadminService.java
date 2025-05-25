package com.example.registration_and_application.service;

import com.example.registration_and_application.dto.AdminRegistrationDto;
import com.example.registration_and_application.enums.Role;

import java.time.LocalDateTime;

public interface SuperadminService {

    public void admin_registration(AdminRegistrationDto dto);

    void account_confirm(String activationtoken, LocalDateTime expiration);
}
