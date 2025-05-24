package com.example.registration_and_application.controller;

import com.example.registration_and_application.enums.Role;
import com.example.registration_and_application.service.SuperadminService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/superadmin")
public class SuperAdminController {

    private final SuperadminService superadminService;

    @PostMapping("/adminRegistraton/{email}")
    public void admin_registration(@PathVariable String email, @RequestParam Role role) {
        superadminService.admin_registration(email, role);
    }
}
