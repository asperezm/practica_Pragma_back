package com.example.clientservice.service;

import java.io.IOException;
import java.util.List;

import com.example.clientservice.entity.Client;

public interface ClientService {
    
    public List<Client> findClientAll();

    public Client createClient(Client client) throws IOException;
    public Client updateClient(Client client);
    public Client deleteClient(Client client);
    public  Client getClient(Long id);
}
