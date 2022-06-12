package com.bootcamp.clientservice.service;

import com.bootcamp.clientservice.model.Client;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ClientService {
    Flux<Client> findAll();
    Mono<Client> createClient(Client client);
    Mono<Client> getClientByDocumentNumber(String dni);
    Mono<Client> updateNaturalPerson (String ruc, Client client);
    Mono<Client> createClientBusiness(Client client);
    Mono<Client> getClientByRuc(String ruc);
    Mono<Client> updateBusiness (String id, Client client);

}
