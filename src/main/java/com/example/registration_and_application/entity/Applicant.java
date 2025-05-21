package com.example.registration_and_application.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "applicants_table")
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String phoneNumber;
    private int age;

    @OneToOne(mappedBy = "applicant")
    private User user;
}
