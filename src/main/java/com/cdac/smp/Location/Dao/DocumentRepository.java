package com.cdac.smp.Location.Dao;


import org.springframework.data.mongodb.repository.MongoRepository;  // ✅ add this
import com.cdac.smp.Location.Model.Documents;

public interface DocumentRepository extends MongoRepository<Documents, String> {
}
