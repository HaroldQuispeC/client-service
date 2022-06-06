package com.bootcamp.clientservice.controller;

import com.bootcamp.clientservice.model.Client;
import com.bootcamp.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/createClientBusiness")
    public Mono<Client> createClientBusiness(@RequestBody Client client){
        return clientService.createClientBusinees(client);
    }
}
