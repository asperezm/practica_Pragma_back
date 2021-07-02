package com.example.imageservice.service;

import com.example.imageservice.entity.Photo;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {
    
    public String addPhoto(MultipartFile file);

    public Photo getPhoto(String id);
}
