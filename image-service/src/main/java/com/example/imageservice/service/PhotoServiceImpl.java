package com.example.imageservice.service;

import java.io.IOException;

import com.example.imageservice.entity.Photo;
import com.example.imageservice.repository.PhotoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service
public class PhotoServiceImpl implements PhotoService{
    
    @Autowired
    private PhotoRepository photoRepo;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional
    public Photo addPhoto(MultipartFile file) throws IOException { 
        Photo photo = new Photo(); 
        photo.setPhoto(file.getBytes());
        photo = photoRepo.save(photo);
        Photo photoDB = modelMapper.map(photo, Photo.class);
        return photoDB; 
    }

    @Override
    public Photo getPhoto(String id) { 
        Photo photoDB = photoRepo.findById(id).orElse(null);
        if (photoDB != null) {
			photoDB = modelMapper.map(photoDB, Photo.class);
		}
		return photoDB;
    }

    @Override
    public Photo addPhoto(Photo photo) {
        // TODO Auto-generated method stub
        Photo photoDB = modelMapper.map(photo, Photo.class);
		photoDB = photoRepo.save(photoDB);
		return photoDB;
    }

    @Override
    public Photo updatePhoto(String id, Photo photo) {
        // TODO Auto-generated method stub
        Photo photoDB = photoRepo.findById(id).orElse(null);
		if (photoDB == null) {
			return null;
		}
		photoDB.setPhoto(photo.getPhoto());
		photoDB = photoRepo.save(photoDB);
		return photoDB;
    }

    @Override
    public void deletePhoto(Photo photo) {
        // TODO Auto-generated method stub
        Photo photoDB = getPhoto(photo.getId());
        if (photoDB == null){
            //return  null;
        }
        photoRepo.delete(photoDB);
    }
}
