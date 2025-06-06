package com.example.registration_and_application.service.serviceImpl;

import com.example.registration_and_application.dto.AdminRegistrationDto;
import com.example.registration_and_application.dto.AdminResponse;
import com.example.registration_and_application.entity.Admin;
import com.example.registration_and_application.entity.User;
import com.example.registration_and_application.exception.BadCredentialsException;
import com.example.registration_and_application.exception.CustomException;
import com.example.registration_and_application.exception.NotFoundException;
import com.example.registration_and_application.mapper.AdminMapper;
import com.example.registration_and_application.repository.AdminRepository;
import com.example.registration_and_application.repository.UserRepository;
import com.example.registration_and_application.service.AuthService;
import com.example.registration_and_application.service.SuperadminService;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class SuperAdminServiceImpl implements SuperadminService {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JavaMailSender mailSender;
    private final AdminMapper adminMapper;
    private final AdminRepository adminRepository;

    public String generateActivationToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void admin_registration(AdminRegistrationDto dto) {
        if (!dto.getEmail().contains("@gmail.com")) {
            throw new CustomException("Email must have @gmail.com", HttpStatus.BAD_REQUEST);
        }

        Random random = new Random();
        String password = String.valueOf(random.nextInt(90000000) + 10000000);
        String activationtoken = generateActivationToken();

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(password));
        user.setRole(dto.getRole());
        user.setActivationtoken(activationtoken);

        Admin admin = new Admin();
        admin.setName(dto.getName());
        admin.setPhoneNumber(dto.getPhoneNumber());
        admin.setAge(dto.getAge());
        admin.setUser(user);

        user.setAdmin(admin);

        userRepository.save(user);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("talantbekovnazar28@gmail.com");
        message.setTo(dto.getEmail());
        message.setSubject("Your account");
        message.setText("Your email: " + dto.getEmail() + "\n" +
                "Your password: " + password + "\n" +
                "Click the link to login into your account: " +
                "http://localhost:8081/superadmin/account_confirm?activationtoken=" + activationtoken);

        mailSender.send(message);
    }

    @Override
    public void admin_removing(String email) {
        if (adminRepository.findByEmail(email).isEmpty())
            throw new NotFoundException("the admin with email: "+email+" is empty!", HttpStatus.BAD_REQUEST);
        adminRepository.deleteByEmail(email);
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

    @Override
    public List<AdminResponse> getAllAdmins() {
        return adminMapper.toDtoS(adminRepository.findAll());
    }
}
