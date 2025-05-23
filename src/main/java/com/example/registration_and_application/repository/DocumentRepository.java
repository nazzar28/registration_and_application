package com.example.registration_and_application.repository;


import com.example.registration_and_application.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
