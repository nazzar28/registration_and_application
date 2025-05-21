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
    private String age;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    private Applicant applicant;

    @OneToOne(cascade = CascadeType.ALL)
    private Admin admin;

    @OneToOne(cascade = CascadeType.ALL)
    private Superadmin superadmin;
}
