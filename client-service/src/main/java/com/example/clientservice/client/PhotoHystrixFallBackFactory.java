package com.example.clientservice.client;

import com.example.clientservice.model.Photo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class PhotoHystrixFallBackFactory implements PhotoClient{

    @Override
    public ResponseEntity<Photo> getPhoto(String id) {
        // TODO Auto-generated method stub
        Photo photo = Photo.builder()
        .id(null)
        .photo(null).build();
        return ResponseEntity.ok(photo);
    }

    @Override
    public ResponseEntity<MultipartFile> addPhoto(MultipartFile image) {
        // TODO Auto-generated method stub
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Photo> updatePhoto(String id, Photo photo) {
        // TODO Auto-generated method stub
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Photo> deletePhoto(String id) {
        // TODO Auto-generated method stub
        return ResponseEntity.ok().build();
    }

}