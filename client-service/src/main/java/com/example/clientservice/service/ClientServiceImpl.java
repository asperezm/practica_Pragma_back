package com.example.clientservice.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.example.clientservice.client.PhotoClient;
import com.example.clientservice.entity.Client;
import com.example.clientservice.model.Photo;
import com.example.clientservice.repository.ClientRepository;
import com.example.clientservice.util.MockMultipartFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;


import org.apache.http.entity.ContentType;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    PhotoClient photoClient;

    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<Client> findClientAll() {
        // TODO Auto-generated method stub
        return clientRepository.findAll();
    }

    @Override
    public Client createClient(Client client) throws IOException {
        // TODO Auto-generated method stub

        Client clientDB = clientRepository.findByNumberID(client.getId());
        if(clientDB != null){
            return clientDB;
        }
        client.setState("CREATED");
        clientDB = clientRepository.save(client);
        Photo photo = null;
		if (client.getPhoto() != null) {
            photo = client.getPhoto();
            byte[] pdfFile = client.getPhoto().getPhoto();
            InputStream inputStream = new ByteArrayInputStream(pdfFile);
            MultipartFile file = new MockMultipartFile("hola.jpg","old.jpg",ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
			MultipartFile photoDB = photoClient.addPhoto(file).getBody();
			if (photoDB != null) {
				clientDB.setPhotoId(photo.getId());
			}
		}
        return clientDB;
    }

    @Override
    public Client updateClient(Client client) throws IOException {
        // TODO Auto-generated method stub
        Client clientDB = getClient(client.getId());
        if(clientDB != null){
            return clientDB;
        }

        clientDB.setFirstName(client.getFirstName());
        clientDB.setLastName(client.getLastName());
        clientDB.setTypeDoc(client.getTypeDoc());
        clientDB.setNumberID(client.getNumberID());
        clientDB.setCity(client.getCity());
        clientDB.setAge(client.getAge());

        Photo photo = null;
		if (client.getPhoto() != null) {
			photo = photoClient.getPhoto(client.getPhoto().getId()).getBody();
			if (photo == null) {
				byte[] pdfFile = client.getPhoto().getPhoto();
                InputStream inputStream = new ByteArrayInputStream(pdfFile);
                MultipartFile file = new MockMultipartFile("hola.jpg","old.jpg",ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
			    MultipartFile photoDB = photoClient.addPhoto(file).getBody();
			} else {
				photo.setPhoto(client.getPhoto().getPhoto());
				photo = photoClient.updatePhoto(photo.getId(),photo).getBody();
			}
			clientDB.setPhotoId(photo.getId());
		}

        return  clientRepository.save(clientDB);
    }

    @Override
    public Client getClient(int id) {
        // TODO Auto-generated method stub
        Client clientDB =  clientRepository.findById(id).orElse(null);
        if (clientDB == null) {
			return clientDB;
		}
        if (clientDB.getPhotoId() != null) {
			Photo photo = photoClient.getPhoto(clientDB.getPhotoId()).getBody();
			clientDB.setPhoto(photo);
		}
		return clientDB;
    }

    @Override
    public void deleteClient(int id) {
        // TODO Auto-generated method stub
        Client clientDB = clientRepository.findById(id).orElse(null);
        if(clientDB == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "client doesn't exist");
        }
        clientRepository.delete(clientDB);
        if(clientDB.getPhotoId() != null){
            photoClient.deletePhoto(clientDB.getPhotoId());
        }
    }

    @Override
    public List<Client> listCustomersByAge(int age) {
        // TODO Auto-generated method stub
        List<Client> clientDB = null;
        clientDB= clientRepository.findByAgeGreaterThanEqual(age);
        return clientDB;
    }
    
}
