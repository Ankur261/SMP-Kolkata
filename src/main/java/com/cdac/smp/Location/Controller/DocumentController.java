package com.cdac.smp.Location.Controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cdac.smp.Location.Model.Documents;
import com.cdac.smp.Location.Service.DocumentService;


@Controller
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestParam("docDesc") String docDesc,
                             @RequestParam("uploadedBy") String uploadedBy,
                             RedirectAttributes redirectAttributes) {
        try {
            String fileId = documentService.saveDocument(file, docDesc, uploadedBy);
            redirectAttributes.addFlashAttribute("message", "File uploaded successfully with ID: " + fileId);
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "File upload failed: " + e.getMessage());
        }
        return "redirect:/locations/view";
    }



    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("id") String id) {
        Optional<Documents> docOpt = documentService.getDocument(id);
        if (docOpt.isPresent()) {
            Documents doc = docOpt.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + doc.getDocFileName() + "\"")
                    .contentType(MediaType.parseMediaType(doc.getDocType()))
                    .body(doc.getFileData().getData()); 
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    @GetMapping("/view/{id}")
    public ResponseEntity<byte[]> viewFile(@PathVariable("id") String id) {
        Optional<Documents> docOpt = documentService.getDocument(id);
        if (docOpt.isPresent()) {
            Documents doc = docOpt.get();
            String fileName = doc.getDocFileName();
            String contentType = doc.getDocType();

            // Force PDF type if filename ends with .pdf
            if (fileName.toLowerCase().endsWith(".pdf")) {
                contentType = "application/pdf";
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(doc.getFileData().getData());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}