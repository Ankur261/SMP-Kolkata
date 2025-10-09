package com.cdac.smp.Location.Service;

import java.time.LocalDateTime;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cdac.smp.Location.Dao.DocumentRepository;
import com.cdac.smp.Location.Model.Documents;



@Service
public class DocumentService {

    private final DocumentRepository documentsRepository;

    public DocumentService(DocumentRepository documentsRepository) {
        this.documentsRepository = documentsRepository;
    }

    // Save document with metadata + file content
    public String saveDocument(MultipartFile file, String desc, String uploadedBy, boolean isTemp) throws IOException {
        Documents doc = new Documents();
        doc.setDocFileId(UUID.randomUUID().toString());
        doc.setDocFileName(file.getOriginalFilename());
        doc.setDocDesc(desc);
        doc.setDocType(file.getContentType());
        doc.setUploadedBy(uploadedBy);
        doc.setUploadedAt(LocalDateTime.now());
        doc.setFileData(new Binary(file.getBytes())); // store actual bytes
        doc.setDelFlag(false);
        doc.setTemp(isTemp);
        documentsRepository.save(doc);
        return doc.getDocFileId();
    }

    // Fetch by id
    public Optional<Documents> getDocument(String id) {
        return documentsRepository.findById(id);
    }
    
    public List<Documents> getAllDocuments() {
        return documentsRepository.findAll();
    }
    
    public Optional<Documents> getLastDraft(String uploadedBy) {
        return documentsRepository.findTopByUploadedByAndIsTempOrderByUploadedAtDesc(uploadedBy, true);
    }
}