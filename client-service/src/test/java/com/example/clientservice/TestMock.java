package com.example.clientservice;

import static org.mockito.Mockito.when;

import com.example.clientservice.client.PhotoClient;
import com.example.clientservice.repository.ClientRepository;
import com.example.clientservice.service.ClientServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.springframework.web.server.ResponseStatusException;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.clientservice.entity.Client;
import com.example.clientservice.model.Photo;


@ExtendWith(MockitoExtension.class)
public class TestMock {

    @Mock
    ClientRepository clientRepository;

    @Mock
    PhotoClient photoClient;

    @InjectMocks
    ClientServiceImpl clientServiceImpl;

    public static Client getClient1(){
        return Client.builder()
            .typeDoc("cc")
            .numberID("100088")
            .firstName("andrea")
            .lastName("lopez")
            .age(30)
            .city("medellin")
            .state("CREATED").build();
    }

    public static Client getClient2(){
        return Client.builder()
            .typeDoc("cc")
            .numberID("48765")
            .firstName("andres")
            .lastName("ospina")
            .age(20)
            .city("bogota")
            .state("CREATED").build();
    }

    public static Client getClientWithPhoto1(){
        return Client.builder()
            .typeDoc("cc")
            .numberID("35489")
            .firstName("esteban")
            .lastName("gomez")
            .age(40)
            .city("medellin")
            .photoId("60e5e1d6bb8764398fc1f66d")
            .state("CREATED").build();
    }

    public static Client getClientWithPhoto2(){
        return Client.builder()
            .typeDoc("cc")
            .numberID("987654")
            .firstName("marlon")
            .lastName("taborda")
            .age(25)
            .city("medellin")
            .photoId("60ef2c0c1577ff4d9580835c")
            .photo(getPhoto1())
            .state("CREATED").build();
    }

    public static Photo getPhoto1() {
		return Photo.builder().id("60e5e1d6bb8764398fc1f66d")
            .photo('/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAYGBgYHBgcICAcKCwoLCg8ODAwODxYQERAREBYiFRkVFRkVIh4kHhweJB42KiYm').build();
	}

    @Test
    public void getCLientWithOutPhoto(){
        when(clientRepository.findByNumberIDAndTypeDoc("100088", "cc")).thenReturn(getClient1());

        Client clientDB = clientServiceImpl.getClient("100088", "cc");

        assertEquals("100088", clientDB.getNumberID());
        assertEquals("andrea", clientDB.getFirstName());
    }

    @Test
    public void getCLientWithPhoto(){
        when(clientRepository.findByNumberIDAndTypeDoc("35489", "cc")).thenReturn(getClientWithPhoto1());

        Client clientDB = clientServiceImpl.getClient("35489", "cc");

        assertEquals("35489", clientDB.getNumberID());
        assertEquals("esteban", clientDB.getFirstName());
        assertEquals("60e5e1d6bb8764398fc1f66d", clientDB.getPhotoId());
    }

    @Test
    public void getCLientNull(){
        when(clientRepository.findByNumberIDAndTypeDoc("1000888557", "cc")).thenReturn(null);

        assertThrows(ResponseStatusException.class, () -> {clientServiceImpl.getClient("1000888557", "cc");});
    }










    
}
