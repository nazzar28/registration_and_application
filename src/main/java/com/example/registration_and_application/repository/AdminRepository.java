package com.example.registration_and_application.repository;

import com.example.registration_and_application.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AdminRepository extends JpaRepository<Admin, Long> {
}