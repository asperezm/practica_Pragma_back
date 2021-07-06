package com.example.clientservice.client;

import org.bson.types.Binary;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "image-service")
@RequestMapping(value = "/photos")
public interface PhotoClient {
    
    @GetMapping("/{id}")
    public ResponseEntity<Binary> getPhoto(@PathVariable String id);

    @PostMapping("/add")
    public ResponseEntity<String> addPhoto(@RequestParam("image") MultipartFile image);
}
