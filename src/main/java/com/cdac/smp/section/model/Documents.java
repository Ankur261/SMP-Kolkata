package com.cdac.smp.section.model;

import java.time.LocalDateTime;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "documents")
public class Documents {
    
    @Id
    private String docFileId;

    private String docFileName;
    private String docDesc;
    private String docType;
    private String uploadedBy;
    private LocalDateTime uploadedAt;

    // actual file content
    private Binary fileData;
}
