package com.example.registration_and_application.service.serviceImpl;

import com.example.registration_and_application.config.JwtService;
import com.example.registration_and_application.dto.AuthLoginRequest;
import com.example.registration_and_application.dto.AuthLoginResponse;
import com.example.registration_and_application.dto.UserRegisterRequest;
import com.example.registration_and_application.entity.User;
import com.example.registration_and_application.enums.Role;
import com.example.registration_and_application.exception.CustomException;
import com.example.registration_and_application.repository.UserRepository;
import com.example.registration_and_application.security.UserDetailsImpl;
import com.example.registration_and_application.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public AuthLoginResponse login(AuthLoginRequest authLoginRequest) {
        Optional<User> user = userRepository.findByEmail(authLoginRequest.getEmail());
        if (user.isEmpty())
            throw new CustomException("User not found", HttpStatus.BAD_REQUEST);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authLoginRequest.getEmail(), authLoginRequest.getPassword()));
        } catch (org.springframework.security.authentication.BadCredentialsException e) {
            throw new CustomException("User not found", HttpStatus.BAD_REQUEST);
        }

        return convertToResponse(user);
    }

    public AuthLoginResponse convertToResponse(Optional<User> user) {
        AuthLoginResponse authLoginResponse = new AuthLoginResponse();
        authLoginResponse.setEmail(user.get().getEmail());
        authLoginResponse.setId(user.get().getId());

        if (user.get().getRole().equals(Role.APPLICANT)) {
            authLoginResponse.setName(user.get().getApplicant().getName());
        } else if (user.get().getRole().equals(Role.ADMIN)) {
            authLoginResponse.setName(user.get().getAdmin().getName());
        } else {
            authLoginResponse.setName(user.get().getSuperadmin().getName());
        }

        Map<String, Object> extraClaims = new HashMap<>();
        UserDetails userDetails = new UserDetailsImpl(user.get());
        String token = jwtService.generateToken(extraClaims, userDetails);
        authLoginResponse.setToken(token);

        return authLoginResponse;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new UserDetailsImpl(user);
    }

    @Override
    public void register(UserRegisterRequest userRegisterRequest) {
        String activationtoken = generateActivationToken();

        if (userRepository.findByEmail(userRegisterRequest.getEmail()).isPresent())
            throw new CustomException("User with email: " + userRegisterRequest.getEmail() + " is already exist!", HttpStatus.BAD_REQUEST);

        User user = new User();

        if (!userRegisterRequest.getEmail().contains("@gmail.com"))
            throw new CustomException("Email must have @gmail.com", HttpStatus.BAD_REQUEST);
        user.setEmail(userRegisterRequest.getEmail());

        user.setRole(Role.APPLICANT);

        // Зарегистрировать пользователя полностью можно, когда понадобится

        // user.setPassword(encoder.encode(userRegisterRequest.getPassword()));
        // user.setActivationtoken(activationtoken);
        // ...
    }
}
