package com.example.registration_and_application.service;

import com.example.registration_and_application.dto.AdminRegistrationDto;
import com.example.registration_and_application.dto.AdminResponse;
import com.example.registration_and_application.enums.Role;

import java.time.LocalDateTime;
import java.util.List;

public interface SuperadminService {

    public void admin_registration(AdminRegistrationDto dto);

    public List<AdminResponse> getAllAdmins();

    void account_confirm(String activationtoken, LocalDateTime expiration);
}
