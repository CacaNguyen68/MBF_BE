package com.nguyenanhthu.ptm.upload_files.config;

import com.nguyenanhthu.ptm.upload_files.service.StorageService;
import com.nguyenanhthu.ptm.upload_files.service.impl.StorageServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {
    @Value("${upload.directory}")
    private String uploadDirectory;

    @Bean
    public StorageService storageService() {
        return new StorageServiceImpl(uploadDirectory);
    }
}
