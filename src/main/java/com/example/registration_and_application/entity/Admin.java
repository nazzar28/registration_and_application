package com.example.registration_and_application.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name = "admins_table")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String phoneNumber;
    private int age;
    private String email;

    @OneToOne(mappedBy = "admin")
    @ToString.Exclude
    private User user;
}
