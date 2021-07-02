package com.example.clientservice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.clientservice.entity.Client;
import com.example.clientservice.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/client")
public class ClientRest {

    @Autowired
    ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> listAllClient(){
        List<Client> clients = new ArrayList<>();
        clients = clientService.findClientAll();
        if(clients.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clients);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> getClient(@PathVariable("id") long id) {
        log.info("Fetching Client with id {}", id);
        Client client = clientService.getClient(id);
        if (  null == client) {
            log.error("Client with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client client, BindingResult result) {
        log.info("Creating Client : {}", client);
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }

       Client clientDB = clientService.createClient (client);

        return  ResponseEntity.status(HttpStatus.CREATED).body(clientDB);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateClient(@PathVariable("id") long id, @RequestBody Client client) {
        log.info("Updating Client with id {}", id);

        Client currentClient = clientService.getClient(id);

        if ( null == currentClient ) {
            log.error("Unable to update. Client with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        client.setId(id);
        currentClient=clientService.updateClient(client);
        return  ResponseEntity.ok(currentClient);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable("id") long id) {
        log.info("Fetching & Deleting Client with id {}", id);

        Client client = clientService.getClient(id);
        if ( null == client ) {
            log.error("Unable to delete. Client with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        client = clientService.deleteClient(client);
        return  ResponseEntity.ok(client);
    }

    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
