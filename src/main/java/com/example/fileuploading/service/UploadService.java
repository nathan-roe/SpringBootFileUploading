package com.example.fileuploading.service;

import com.example.fileuploading.utility.exceptions.FileStorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadService {
    @Value("storage")
    public String uploadDir;

    public void uploadFile(MultipartFile file) throws FileStorageException {
        try {
            Path copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            try {
                Files.createFile(copyLocation);
            } finally {
                Files.write(copyLocation, file.getBytes());
            }
        } catch(Exception e) {
            e.printStackTrace();
            throw new FileStorageException("Could not store file " + file.getOriginalFilename() + ".");
        }
    }
}
