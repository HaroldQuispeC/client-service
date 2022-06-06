package com.bootcamp.clientservice.service;

import com.bootcamp.clientservice.model.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {
    Flux<Client> findAll();
    Mono<Client> createClient(Client client);
    Mono<Client> createClientBusinees(Client client);

}
