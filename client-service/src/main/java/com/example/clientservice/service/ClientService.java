package com.example.clientservice.service;

import java.io.IOException;
import java.util.List;

import com.example.clientservice.entity.Client;

public interface ClientService {
    
    public List<Client> findClientAll();

    public Client createClient(Client client) throws IOException;
    public Client updateClient(Client client) throws IOException;
    public void deleteClient(int id);
    public  Client getClient(int id);
    public List<Client> listCustomersByAge(int age);
    public Client getClient(String numberID,String typeDoc);
}
