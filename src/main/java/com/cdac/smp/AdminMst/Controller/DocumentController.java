  package com.cdac.smp.AdminMst.Controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cdac.smp.AdminMst.Model.Documents;
import com.cdac.smp.AdminMst.Service.DocumentService;

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
        System.out.print("Hello");
        redirectAttributes.addFlashAttribute("message", "File uploaded successfully with ID: " + fileId);
        return "redirect:success";
    }
    
    @GetMapping("/success")
    public String showConfirmationPage() {
        return "AdminMst/Success";
    }

    
   
}