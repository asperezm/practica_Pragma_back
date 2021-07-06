package com.example.imageservice.service;

import java.io.IOException;

import com.example.imageservice.entity.Photo;
import com.example.imageservice.repository.PhotoRepository;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class PhotoServiceImpl implements PhotoService{
    
    @Autowired
    private PhotoRepository photoRepo;

    public String addPhoto(MultipartFile file) throws IOException { 
        Photo photo = new Photo(); 
        photo.setPhoto(new Binary(BsonBinarySubType.BINARY, file.getBytes())); 
        photo = photoRepo.insert(photo); 
        return photo.getId(); 
    }

    public Photo getPhoto(String id) { 
        return photoRepo.findById(id).get(); 
    }
}
