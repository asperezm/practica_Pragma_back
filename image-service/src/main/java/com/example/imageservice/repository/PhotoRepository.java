package com.example.imageservice.repository;

import com.example.imageservice.entity.Photo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoRepository extends MongoRepository<Photo,String>{
}
