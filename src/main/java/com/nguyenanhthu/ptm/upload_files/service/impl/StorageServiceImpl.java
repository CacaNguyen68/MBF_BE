package com.nguyenanhthu.ptm.upload_files.service.impl;

import com.nguyenanhthu.ptm.upload_files.exceptions.StorageException;
import com.nguyenanhthu.ptm.upload_files.exceptions.StorageFileNotFoundException;
import com.nguyenanhthu.ptm.upload_files.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Service
public class StorageServiceImpl implements StorageService {

    private final Path uploadPath;

    @Autowired
    public StorageServiceImpl(@Value("${upload.directory}") String uploadDirectory) {
        this.uploadPath = Paths.get(uploadDirectory).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.uploadPath);
        } catch (Exception ex) {
            throw new StorageException("Could not create upload directory", ex);
        }
    }

    @Override
    public String store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                log.error("Failed to store empty file " + filename);
                throw new StorageException("Failed to store empty file " + filename);
            }
            Path targetLocation = this.uploadPath.resolve(filename);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return filename;
        } catch (IOException ex) {
            log.error("Failed to store file " + filename, ex);
            throw new StorageException("Failed to store file " + filename, ex);
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path filePath = this.uploadPath.resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                log.error("File not found: " + filename);
                throw new StorageFileNotFoundException("File not found: " + filename);
            }
        } catch (MalformedURLException ex) {
            log.error("File not found: " + filename, ex);
            throw new StorageFileNotFoundException("File not found: " + filename, ex);
        }
    }
}

