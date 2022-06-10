package com.bootcamp.clientservice.service;

import com.bootcamp.clientservice.model.Client;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {
    Flux<Client> findAll();
    Mono<Client> createClient(Client client);
    Mono<Client> getClientByDocumentNumber(String dni);
    Mono<ResponseEntity<Client>> updateNaturalPerson (String id, Client client);
    Mono<Client> createClientBusiness(Client client);
    Mono<Client> getClientByRuc(String ruc);
    Mono<ResponseEntity<Client>> updateBusiness (String id, Client client);

}
