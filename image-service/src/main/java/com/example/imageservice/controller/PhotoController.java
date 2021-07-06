package com.example.imageservice.controller;

import java.io.IOException;

import com.example.imageservice.entity.Photo;
import com.example.imageservice.service.PhotoService;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/photos")
public class PhotoController {

    @Autowired
    PhotoService photoService;
    
    @GetMapping("/{id}")
    public ResponseEntity<Binary> getPhoto(@PathVariable String id) {
        Photo photo = photoService.getPhoto(id);
    
        Binary image = photo.getPhoto();
        return ResponseEntity.ok(image);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addPhoto(@RequestParam("image") MultipartFile image) throws IOException {
        String id = photoService.addPhoto(image);
        return ResponseEntity.ok(id);
    }
}
