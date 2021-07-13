package com.example.clientservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import com.example.clientservice.model.Photo;

@FeignClient(name = "image-service", path= "/photos", fallback = PhotoHystrixFallBackFactory.class)
public interface PhotoClient {
    
    @GetMapping("/{id}")
    public ResponseEntity<Photo> getPhoto(@PathVariable("id") String id);

    @PostMapping("/add")
    public ResponseEntity<MultipartFile> addPhoto(@RequestParam("image") MultipartFile image);

    @PutMapping(value = "/{id}")
	public ResponseEntity<Photo> updatePhoto(@PathVariable("id") String id, @RequestBody(required = true) Photo photo);
}
