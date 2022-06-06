package com.bootcamp.clientservice.service.impl;

import com.bootcamp.clientservice.model.Client;
import com.bootcamp.clientservice.repository.ClientRepository;
import com.bootcamp.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Flux<Client> findAll() {

        return clientRepository.findAll();
    }

    @Override
    public Mono<Client> createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Mono<Client> createClientBusinees(Client client) {
        return clientRepository.save(client);
    }

}
