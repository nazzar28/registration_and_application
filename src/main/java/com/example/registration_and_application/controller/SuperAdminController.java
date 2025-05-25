package com.example.registration_and_application.controller;

import com.example.registration_and_application.dto.AdminRegistrationDto;
import com.example.registration_and_application.enums.Role;
import com.example.registration_and_application.service.SuperadminService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/superadmin")
public class SuperAdminController {

    private final SuperadminService superadminService;

    @PostMapping("/adminRegistration")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public void adminRegistration(@RequestBody AdminRegistrationDto dto) {
        superadminService.admin_registration(dto);
    }
}
