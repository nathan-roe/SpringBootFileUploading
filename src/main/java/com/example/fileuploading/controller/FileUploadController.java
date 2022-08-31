package com.example.fileuploading.controller;

import com.example.fileuploading.service.UploadService;
import com.example.fileuploading.utility.exceptions.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@RestController
@RequestMapping("/upload")
public class FileUploadController {
    private final UploadService uploadService;

    @Autowired
    public FileUploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws FileStorageException {
        System.out.println("HERE!");
        uploadService.uploadFile(file);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/multiple")
    public ResponseEntity<?> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files)
            throws FileStorageException {
        Arrays.asList(files).stream().forEach(file -> uploadService.uploadFile(file));
        return ResponseEntity.accepted().build();
    }
}
