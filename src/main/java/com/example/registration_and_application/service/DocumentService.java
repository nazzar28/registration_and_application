package com.example.registration_and_application.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DocumentService {
    void uploadDocument(MultipartFile file, Long applicantId) throws IOException;
}
