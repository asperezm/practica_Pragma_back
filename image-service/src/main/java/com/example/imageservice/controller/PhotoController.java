package com.example.imageservice.controller;

import java.io.IOException;

import com.example.imageservice.entity.Photo;
import com.example.imageservice.service.PhotoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/photos")
public class PhotoController {

    @Autowired
    PhotoService photoService;
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Get photo by id.", notes = "Provide an ID to look up specific photo.")
    public ResponseEntity<Photo> getPhoto(@PathVariable String id) {
        Photo photo = photoService.getPhoto(id);
        if (photo == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(photo);
    }

    @PostMapping("/add")
    @ApiOperation(value = "Add photo.", notes = "Provide photo to create it.")
    public ResponseEntity<Photo> addPhoto(@RequestParam("image") MultipartFile image) throws IOException {
        Photo photoDB = photoService.addPhoto(image);
        return ResponseEntity.status(HttpStatus.CREATED).body(photoDB);
    }

    @PostMapping
	@ApiOperation(value = "add photo.", notes = "Provide photo data to create it.")
	public ResponseEntity<Photo> addPhoto(@RequestBody(required = true) Photo photo) {
		Photo photoDB = photoService.addPhoto(photo);
		return ResponseEntity.status(HttpStatus.CREATED).body(photoDB);
	}

    @PutMapping(value = "/{id}")
	@ApiOperation(value = "Update photo.", notes = "Provide photo data to update it.")
	public ResponseEntity<Photo> updatePhoto(@PathVariable("id") String id, @RequestBody(required = true) Photo photo) {
		Photo photoDB = photoService.updatePhoto(id,photo);
		return ResponseEntity.ok(photoDB);
	}
}
