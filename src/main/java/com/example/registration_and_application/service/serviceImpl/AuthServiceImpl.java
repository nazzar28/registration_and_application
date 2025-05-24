package com.example.registration_and_application.service.serviceImpl;

import com.example.registration_and_application.config.JwtService;
import com.example.registration_and_application.dto.UserRegisterRequest;
import com.example.registration_and_application.entity.User;
import com.example.registration_and_application.enums.Role;
import com.example.registration_and_application.exception.CustomException;
import com.example.registration_and_application.repository.UserRepository;
import com.example.registration_and_application.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;
    private PasswordEncoder encoder;
    private AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public String generateActivationToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void register(UserRegisterRequest userRegisterRequest) {
        String activationtoken = generateActivationToken();

        if (userRepository.findByEmail(userRegisterRequest.getEmail()).isPresent())
            throw new CustomException("User with email: " + userRegisterRequest.getEmail() + " is already exist!", HttpStatus.BAD_REQUEST);

        User user = new User();

        if(!userRegisterRequest.getEmail().contains("@gmail.com"))
            throw new CustomException("Email must have @gmail.com", HttpStatus.BAD_REQUEST);
        user.setEmail(userRegisterRequest.getEmail());

        user.setRole(Role.APPLICANT);

//        if(userRegisterRequest.getPassword().isEmpty())
//            throw new CustomException("Password cannot be empty", HttpStatus.BAD_REQUEST);
//        user.setPassword(encoder.encode(userRegisterRequest.getPassword()));
//
//        user.setActivationtoken(activationtoken);
//
//        Student student=new Student();
//
//        if(userRegisterRequest.getNickName().isEmpty())
//            throw new CustomException("Nickname field cannot be empty", HttpStatus.BAD_REQUEST);
//        student.setNickName(userRegisterRequest.getNickName());
//
//        if(userRegisterRequest.getAge().toString().isEmpty())
//            throw new CustomException("Age cannot be empty", HttpStatus.BAD_REQUEST);
//        student.setAge(userRegisterRequest.getAge());
//
//        if(userRegisterRequest.getGender().toString().isEmpty())
//            throw new CustomException("Gender cannot be empty", HttpStatus.BAD_REQUEST);
//        student.setGender(userRegisterRequest.getGender());
//
//        student.setUser(user);
//        user.setStudent(student);
//        userRepository.save(user);
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("aslan.tabaldiev@gmail.com");
//        message.setTo(userRegisterRequest.getEmail());
//        message.setSubject("Confirm registration");
//        message.setText("""
//                Click the link below to confirm your registration
//
//                http://localhost:8081/auth/registration_confirm?activationtoken=""" + activationtoken);
//
//        mailSender.send(message);
    }

//    @Override
//    public void password_reset(String email) {
//        LocalDateTime expirationTime = LocalDateTime.now().plusHours(24);
//        Random random = new Random();
//        Integer resetcode = random.nextInt(9000) + 1000;
//
////        User user = userRepository.findUserByEmail(email);
////        if(user == null)
////            throw new CustomException("User not found", HttpStatus.BAD_REQUEST);
////
////        SimpleMailMessage message = new SimpleMailMessage();
////        message.setFrom("aslan.tabaldiev@alatoo.edu.kg");
////        message.setTo(email);
////        message.setSubject("Password reset code");
////        message.setText("Your password reset code: " + resetcode + "\n\n" +
////                        "This code will expire in 24 hours");
////
////        mailSender.send(message);
////
////        user.setCode(resetcode);
////        user.setCode_expiration(expirationTime);
//
//        userRepository.save(user);
//    }
}