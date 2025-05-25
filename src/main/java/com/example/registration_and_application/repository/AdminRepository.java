package com.example.registration_and_application.repository;

import com.example.registration_and_application.entity.Admin;
import com.example.registration_and_application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findById(Long id);

    Optional<Admin> findByEmail(String email);

    public void deleteByEmail(String email);
}