package com.cdac.smp.section.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cdac.smp.section.model.Documents;
import com.cdac.smp.section.service.DocumentService;

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
                             RedirectAttributes redirectAttributes) throws IOException {
        String fileId = documentService.saveDocument(file, docDesc, uploadedBy);
        redirectAttributes.addFlashAttribute("message", "File uploaded successfully with ID: " + fileId);
        return "redirect:/"; // back to home
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String id) {
        Optional<Documents> docOpt = documentService.getDocument(id);
        System.out.print(docOpt);
        if (docOpt.isPresent()) {
            Documents doc = docOpt.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + doc.getDocFileName() + "\"")
                    .contentType(MediaType.parseMediaType(doc.getDocType()))
                    .body(doc.getFileData().getData()); // return actual bytes
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    @GetMapping("/all")
    public String getAllDocuments(Model model) {
    	
    	System.out.print(documentService.getAllDocuments());
    	return "" ;
    }
    
    @GetMapping("/form")
    public String getForm(Model model) {
    	
    	model.addAttribute("document", new Documents());
    	return "sectionView/documentForm" ;
    }
}
