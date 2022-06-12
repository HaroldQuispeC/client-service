package com.bootcamp.clientservice.controller;

import com.bootcamp.clientservice.model.Client;
import com.bootcamp.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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
    public Mono<Client> getClientByDocumentNumber(@PathVariable("dni") String dni){
        return clientService.getClientByDocumentNumber(dni);
    }

    @PutMapping("/updateClient/{dni}")
    public Mono<Client> updateNaturalPerson(@PathVariable("dni") String dni, @RequestBody Client client){
        return clientService.updateNaturalPerson(dni,client);
    }

    @PostMapping("/createClientBusiness")
    public Mono<Client> createClientBusiness(@RequestBody Client client){
        return clientService.createClientBusiness(client);
    }

    @GetMapping("/findByRuc/{ruc}")
    public Mono<Client> findByRuc(@PathVariable("ruc") String ruc){

        return clientService.getClientByRuc(ruc);
    }

    @PutMapping("/updateBusiness/{ruc}")
    public Mono<Client> updateBusiness(@PathVariable("ruc") String ruc, @RequestBody Client client){
        return clientService.updateBusiness(ruc,client);
    }


}
