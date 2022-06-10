package com.bootcamp.clientservice.controller;

import com.bootcamp.clientservice.model.Client;
import com.bootcamp.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/")
    public Flux<Client> getAll(){

        return clientService.findAll();
    }

    @PostMapping("/createClient")
    public Mono<Client> createClient(@RequestBody Client client){
        return clientService.createClient(client);
    }

    @GetMapping("/findByDocument/{dni}")
    public Mono<Client> findByDocumentNumber(@PathVariable("dni") String dni){
        return clientService.getClientByDocumentNumber(dni);
    }

    @PutMapping("/updateClient/{id}")
    public Mono<ResponseEntity<Client>> updateNaturaPerson(@PathVariable("id") String id, @RequestBody Client client){
        return clientService.updateNaturalPerson(id,client);
    }

    @PostMapping("/createClientBusiness")
    public Mono<Client> createClientBusiness(@RequestBody Client client){
        return clientService.createClientBusiness(client);
    }

    @GetMapping("/findByRuc/{ruc}")
    public Mono<Client> findByRuc(@PathVariable("ruc") String ruc){

        return clientService.getClientByRuc(ruc);
    }

    @PutMapping("/updateBusiness/{id}")
    public Mono<ResponseEntity<Client>> updateBusiness(@PathVariable("id") String id, @RequestBody Client client){
        return clientService.updateBusiness(id,client);
    }


}
