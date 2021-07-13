package com.example.clientservice.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Photo {
    
    private String id;
        
    private byte[] photo;
}
