package com.example.imageservice.entity;

import javax.persistence.Id;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
public class Photo {
    
    @Id
    private String id;
        
    private Binary photo;
}
