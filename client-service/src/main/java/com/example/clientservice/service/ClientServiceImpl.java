package com.example.clientservice.service;

import java.util.List;

import com.example.clientservice.entity.Client;
import com.example.clientservice.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;


public class ClientServiceImpl implements ClientService{

    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<Client> findClientAll() {
        // TODO Auto-generated method stub
        return clientRepository.findAll();
    }

    @Override
    public Client createClient(Client client) {
        // TODO Auto-generated method stub

        Client clientDB = clientRepository.findByNumberID(client.getNumberID());
        if(clientDB != null){
            return clientDB;
        }
        client.setState("CREATED");
        clientDB = clientRepository.save(client);
        return clientDB;
    }

    @Override
    public Client updateClient(Client client) {
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
        clientDB.setPhotoId(client.getPhotoId());

        return  clientRepository.save(clientDB);
    }

    @Override
    public Client deleteClient(Client client) {
        // TODO Auto-generated method stub

       Client clientDB = getClient(client.getId());
        if (clientDB ==null){
            return  null;
        }
        client.setState("DELETED");
        return clientRepository.save(client);
    }

    @Override
    public Client getClient(Long id) {
        // TODO Auto-generated method stub
        return clientRepository.findById(id).orElse(null);
    }
    
}
