package com.cdac.smp.section.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cdac.smp.section.model.Documents;
import com.cdac.smp.section.repository.DocumentRepository;

@Service
public class DocumentService {

    private final DocumentRepository documentsRepository;

    public DocumentService(DocumentRepository documentsRepository) {
        this.documentsRepository = documentsRepository;
    }

    // Save document with metadata + file content
    public String saveDocument(MultipartFile file, String desc, String uploadedBy) throws IOException {
        Documents doc = new Documents();
        doc.setDocFileId(UUID.randomUUID().toString());
        doc.setDocFileName(file.getOriginalFilename());
        doc.setDocDesc(desc);
        doc.setDocType(file.getContentType());
        doc.setUploadedBy(uploadedBy);
        doc.setUploadedAt(LocalDateTime.now());
        doc.setFileData(new Binary(file.getBytes())); // store actual bytes

        documentsRepository.save(doc);
        return doc.getDocFileId();
    }

    // Fetch by id
    public Optional<Documents> getDocument(String id) {
        return documentsRepository.findById(id);
    }
}
