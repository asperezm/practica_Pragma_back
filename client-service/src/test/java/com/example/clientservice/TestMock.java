package com.example.clientservice;

import com.example.clientservice.client.PhotoClient;
import com.example.clientservice.repository.ClientRepository;
import com.example.clientservice.service.ClientServiceImpl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestMock {

    @Mock
    ClientRepository clientRepository;

    @Mock
    PhotoClient photoClient;

    @InjectMocks
    ClientServiceImpl clientServiceImpl;

    

    
}
