package com.example.registration_and_application.service.serviceImpl;

import com.example.registration_and_application.entity.Applicant;
import com.example.registration_and_application.entity.Document;
import com.example.registration_and_application.repository.ApplicantRepository;
import com.example.registration_and_application.repository.DocumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor

public class DocumentServiceImpl {

    private final DocumentRepository documentRepository;
    private final ApplicantRepository applicantRepository;

    private final String UPLOAD_DIR = "/home/uploads/documents/";

    public void uploadDocument(MultipartFile file, Long applicantId) throws IOException{
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));

        Files.createDirectories(Paths.get(UPLOAD_DIR));
        String filePath = UPLOAD_DIR + System.currentTimeMillis() + "_" + file.getOriginalFilename();

        Path path = Paths.get(filePath);
        Files.write(path, file.getBytes());

        Document document = Document.builder()
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .filePath(filePath)
                .uploadedAt(LocalDateTime.now())
                .applicant(applicant)
                .build();

        documentRepository.save(document);
    }
}
