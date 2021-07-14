package com.example.clientservice.repository;

import java.util.List;

import com.example.clientservice.entity.Client;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Integer>{
    public Client findByNumberID(int numberID);
    public List<Client> findByLastName(String lastName);
    public List<Client> findByAgeGreaterThanEqual(int age);
}
