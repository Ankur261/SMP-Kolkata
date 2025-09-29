package com.cdac.smp.section.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cdac.smp.section.model.Documents;

public interface DocumentRepository extends MongoRepository<Documents, String> {
}
