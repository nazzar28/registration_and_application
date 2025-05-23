package com.example.registration_and_application.repository;

import com.example.registration_and_application.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    Optional<Applicant> findByEmail(String email);

    // List<Applicant> findByStatus(String status);
}