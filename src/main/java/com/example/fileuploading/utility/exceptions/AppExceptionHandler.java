package com.example.fileuploading.utility.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<?> handleFileException(FileStorageException exception) {
        return ResponseEntity.badRequest().body(exception.getMsg());
    }
}
