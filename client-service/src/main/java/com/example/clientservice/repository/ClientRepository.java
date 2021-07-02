package com.example.clientservice.repository;

import java.util.List;

import com.example.clientservice.entity.Client;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long>{
    public Client findByNumberID(String numberID);
    public List<Client> findByLastName(String lastName);
}
