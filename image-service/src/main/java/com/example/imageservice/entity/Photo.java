package com.example.imageservice.entity;

import javax.persistence.Id;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collation = "photos")
public class Photo {
    
    @Id
    private String id;
        
    private Binary photo;
}
