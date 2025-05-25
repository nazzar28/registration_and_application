package com.example.registration_and_application.config;

import com.example.registration_and_application.entity.Superadmin;
import com.example.registration_and_application.entity.User;
import com.example.registration_and_application.enums.Role;
import com.example.registration_and_application.repository.UserRepository;
import com.example.registration_and_application.repository.SuperadminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final SuperadminRepository superadminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        String superadminEmail = "zavryonok28@gmail.com";

        var userOpt = userRepository.findByEmail(superadminEmail);

        if (userOpt.isPresent()) {
            User existingUser = userOpt.get();
            System.out.println("ℹ️ Superadmin user уже существует: " + existingUser);
            // Дополнительно можно вывести конкретные поля:
            System.out.println("Email: " + existingUser.getEmail());
            System.out.println("Role: " + existingUser.getRole());
            System.out.println("User ID: " + existingUser.getId());
        } else {
            User user = new User();
            user.setEmail(superadminEmail);
            user.setPassword(passwordEncoder.encode("1234"));
            user.setAge("35");
            user.setRole(Role.SUPERADMIN);

            Superadmin superadmin = new Superadmin();
            superadmin.setName("Nazar");
            superadmin.setPhoneNumber("+996700000000");
            superadmin.setAge(35);

            // Связываем сущности
            user.setSuperadmin(superadmin);
            superadmin.setUser(user);

            userRepository.save(user);

            System.out.println("✅ User ID: " + user.getId());
            System.out.println("✅ Superadmin ID: " + superadmin.getId());
        }
    }
}
