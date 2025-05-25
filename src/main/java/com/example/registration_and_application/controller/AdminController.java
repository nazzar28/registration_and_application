package com.example.registration_and_application.controller;

import com.example.registration_and_application.service.AdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    private final AdminService adminService;


    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/send-test-email")
    public String sendTestEmail() {
        adminService.sendMail("nebogaolga09@gmail.com","Email Test","Hello user Dan");
        return "Email sent successfully";    }
}
