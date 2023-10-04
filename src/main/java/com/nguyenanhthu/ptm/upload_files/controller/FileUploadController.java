package com.nguyenanhthu.ptm.upload_files.controller;

import com.nguyenanhthu.ptm.upload_files.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FileUploadController {
    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadImages(@RequestParam("files") List<MultipartFile> imageFiles) {
        List<String> imageUrls = new ArrayList<>();

        for (MultipartFile imageFile : imageFiles) {
            String filename = storageService.store(imageFile);
            String imageUrl = filename;
            imageUrls.add(imageUrl);
        }

        return ResponseEntity.ok(imageUrls);
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        Resource imageResource = storageService.load(filename);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Thay đổi kiểu MIME tùy theo loại hình ảnh
                .body(imageResource);
    }
}
