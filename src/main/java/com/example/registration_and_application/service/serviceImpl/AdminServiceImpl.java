package com.example.registration_and_application.service.serviceImpl;

import com.example.registration_and_application.service.AdminService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private JavaMailSender mailSender;

    @Override
    public void sendMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("talantbekovnazar28@gmail.com");
        message.setTo(to);
        message.setSubject("Подтверждение регистрации");
        message.setText(content);

        mailSender.send(message);

    }
}
