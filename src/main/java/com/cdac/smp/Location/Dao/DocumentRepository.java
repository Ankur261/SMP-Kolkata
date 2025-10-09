package com.cdac.smp.Location.Dao;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;  // ✅ add this
import com.cdac.smp.Location.Model.Documents;

public interface DocumentRepository extends MongoRepository<Documents, String> {
	
	Optional<Documents> findTopByUploadedByAndIsTempOrderByUploadedAtDesc(String uploadedBy, boolean isTemp);
	

}
