package com.example.registration_and_application.controller;

import com.example.registration_and_application.dto.AuthLoginRequest;
import com.example.registration_and_application.dto.AuthLoginResponse;
import com.example.registration_and_application.dto.UserRegisterRequest;
import com.example.registration_and_application.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    // Добавил @RequestBody, чтобы данные приходили из JSON тела запроса
    @PostMapping("/register")
    public void register(@RequestBody UserRegisterRequest userRegisterRequest) {
        authService.register(userRegisterRequest);
    }

    // Добавил @RequestBody, чтобы принимать логин данные из JSON
    @PostMapping("/login")
    public AuthLoginResponse login(@RequestBody AuthLoginRequest authLoginRequest) {
        return authService.login(authLoginRequest);
    }

//
//    @GetMapping("/registration_confirm")
//    public String registration_confirm(@RequestParam String activationtoken) {
//        authService.registration_confirm(activationtoken);
//        return "Your account was successfully activated";
//    }
//
//    @PostMapping("/password_reset/{email}")
//    public String password_reset(@PathVariable String email) {
//        authService.password_reset(email);
//        return "Your password reset code was sent";
//    }
//
//    @PostMapping("/new_password_confirm")
//    public String password_confirm(@RequestBody NewPasswordRequest newPasswordRequest, @RequestParam Integer code) {
//        authService.password_confirm(newPasswordRequest, code);
//        return "Your password was updated";
//    }
//
//    @PostMapping("/password_reset_code_check")
//    public void code_check(@RequestParam Integer code) {
//        authService.code_check(code);
//    }
}