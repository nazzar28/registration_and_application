package com.example.registration_and_application.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "superadmins_table")
public class Superadmin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String phoneNumber;
    private int age;

    @OneToOne(mappedBy = "superadmin")
    private User user;
}