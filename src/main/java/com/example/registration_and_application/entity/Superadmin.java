package com.example.registration_and_application.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name = "superadmins_table")
public class Superadmin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String phoneNumber;
    private int age;

    @OneToOne
    @JoinColumn(name = "user_id")// внешний ключ здесь
    @ToString.Exclude
    private User user;
}
