package com.example.registration_and_application.service.serviceImpl;

import com.example.registration_and_application.entity.Admin;
import com.example.registration_and_application.entity.User;
import com.example.registration_and_application.enums.Role;
import com.example.registration_and_application.exception.BadCredentialsException;
import com.example.registration_and_application.exception.CustomException;
import com.example.registration_and_application.repository.UserRepository;
import com.example.registration_and_application.service.SuperadminService;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class SuperAdminServiceImpl implements SuperadminService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JavaMailSender mailSender;

    public String generateActivationToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void admin_registration(String email, Role role) {
        Random random = new Random();
        String password = String.valueOf(random.nextInt(90000000) + 10000000);
        String activationtoken = generateActivationToken();

        User user = new User();

        if(!email.contains("@gmail.com")) {
            throw new CustomException("Email must have @gmail.com", HttpStatus.BAD_REQUEST);
        }
        user.setEmail(email);

        user.setPassword(encoder.encode(password));
        user.setRole(Role.ADMIN);
        user.setActivationtoken(activationtoken);

        Admin admin=new Admin();
        admin.setName(user.getAdmin().getName());
        admin.setAge(user.getAdmin().getAge());
        admin.setPhoneNumber(user.getAdmin().getPhoneNumber());
        admin.setUser(user);

        user.setAdmin(admin);

        userRepository.save(user);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("talantbekovnazar28@gmail.com");
        message.setTo(email);
        message.setSubject("Your account");
        message.setText("Your email: " + email + "\n" +
                "Your password: " + password + "\n" +
                "Click to the link to login into your account: " +
                "http://localhost:8081/superadmin/account_confirm?activationtoken=" + activationtoken);

        mailSender.send(message);
    }

    @Override
    public void account_confirm(String activationtoken, LocalDateTime expiration) {
        User user = userRepository.findByActivationtoken(activationtoken);

        if(user == null) {
            throw new BadCredentialsException("User not found");
        }

        LocalDateTime now = LocalDateTime.now();

        if(now.isAfter(expiration)) {
            throw new RuntimeException("The link is expired");
        }
        else {
            user.setActivationtoken("Activated");

            userRepository.save(user);
        }
    }
}
