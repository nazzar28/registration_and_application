package com.example.registration_and_application.repository;

import com.example.registration_and_application.entity.Superadmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperadminRepository extends JpaRepository<Superadmin, Long> {
}
