package com.example.registration_and_application.entity;

import com.example.registration_and_application.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
    private String password;
    private String age; // рекомендую заменить на Integer

    @Enumerated(EnumType.STRING)
    private Role role;
    private String activationtoken;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Superadmin superadmin;

    // Остальные связи оставьте как есть
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "applicant_id", referencedColumnName = "id")
    private Applicant applicant;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private Admin admin;
}
