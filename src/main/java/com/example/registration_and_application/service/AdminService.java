package com.example.registration_and_application.service;

import org.springframework.stereotype.Service;

public interface AdminService {
    void sendMail(String to, String subject, String content);
}
