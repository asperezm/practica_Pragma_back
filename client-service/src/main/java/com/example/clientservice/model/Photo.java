package com.example.clientservice.model;

import org.bson.types.Binary;

import lombok.Data;

@Data
public class Photo {
    
    private String id;
        
    private Binary photo;
}
