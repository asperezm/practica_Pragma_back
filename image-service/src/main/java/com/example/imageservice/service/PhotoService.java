package com.example.imageservice.service;

import java.io.IOException;

import com.example.imageservice.entity.Photo;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {
    
    public Photo addPhoto(MultipartFile file) throws IOException;

    public Photo getPhoto(String id);
}
