package com.bootcamp.clientservice.service.impl;

import com.bootcamp.clientservice.model.Client;
import com.bootcamp.clientservice.repository.ClientRepository;
import com.bootcamp.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Mono<Client> getClientByDocumentNumber(String dni) {

        Mono<Client> retrievedClientByDni = clientRepository.findAll()
                .filter(client -> client.getNaturalPerson().getDocumentNumber().equals(dni))
                .single();
        return retrievedClientByDni;

    }

    @Override
    public Mono<ResponseEntity<Client>> updateNaturalPerson(String id, Client client) {
        return clientRepository.findById(id)
                .flatMap(oldClient -> {
                    oldClient.setAddress(client.getAddress());
                    oldClient.setClientType(client.getClientType());
                    oldClient.setStatus(client.getStatus());
                    return clientRepository.save(oldClient);
                })
                .map(newClient -> new ResponseEntity<>(newClient, HttpStatus.OK))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<Client> createClientBusiness(Client client) {

        return clientRepository.save(client);
    }

    @Override
    public Mono<Client> getClientByRuc(String ruc) {
        Mono<Client> retrieveClientByRuc = clientRepository.findAll()
                .filter(client -> client.getBusiness().getRuc().equals(ruc)).take(1).single();
        return retrieveClientByRuc;
    }

    @Override
    public Mono<ResponseEntity<Client>> updateBusiness(String id, Client client) {
        return clientRepository.findById(id)
                .flatMap(oldClienBusiness -> {
                    oldClienBusiness.setAddress(client.getAddress());
                    oldClienBusiness.setClientType(client.getClientType());
                    oldClienBusiness.setStatus(client.getStatus());
                    return clientRepository.save(oldClienBusiness);
                })
                .map(newClient -> new ResponseEntity<>(newClient, HttpStatus.OK))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
