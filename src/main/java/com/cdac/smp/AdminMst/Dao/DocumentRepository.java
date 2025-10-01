package com.cdac.smp.AdminMst.Dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cdac.smp.AdminMst.Model.Documents;

public interface DocumentRepository extends MongoRepository<Documents, String> {
}