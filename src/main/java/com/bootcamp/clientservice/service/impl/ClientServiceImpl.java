package com.bootcamp.clientservice.service.impl;

import com.bootcamp.clientservice.model.Client;
import com.bootcamp.clientservice.model.Constant;
import com.bootcamp.clientservice.repository.ClientRepository;
import com.bootcamp.clientservice.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService {

    private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Flux<Client> findAll() {
        logger.info("Create list of client");
        return clientRepository.findAll();
    }

    @Override
    public Mono<Client> createClient(Client client) {
        logger.info("Create entity client");
        return clientRepository.save(client);
    }

    @Override
    public Mono<Client> getClientByDocumentNumber(String dni) {

        if(dni.isEmpty()){
            throw new RuntimeException("DNI is not valid");
        }

        logger.info("Get entity client by Document Number");
        return clientRepository.findAll()
                .filter(client -> client.getClientType().getClientTypeId().equals(Constant.IncomeAccountTypeId.PERSONAL_CREDIT_ID.type) &&
                        client.getNaturalPerson().getDocumentNumber().trim().equals(dni)).singleOrEmpty();
    }

    @Override
    public Mono<ResponseEntity<Client>> updateBusiness(String id, Client client) {
        logger.info("Update business client entity by id");
        return clientRepository.findById(id)
                .flatMap(oldClient -> {
                    oldClient.setAddress(client.getAddress());
                    oldClient.setClientType(client.getClientType());
                    oldClient.setStatus(client.getStatus());
                    oldClient.setJoiningDate(client.getJoiningDate());
                    oldClient.setAddress(client.getAddress());
                    oldClient.setClientType(client.getClientType());
                    oldClient.setStatus(client.getStatus());
                    oldClient.setPhones(client.getPhones());
                    oldClient.setEmails(client.getEmails());
                    oldClient.getBusiness().setBusinessName(client.getBusiness().getBusinessName());
                    return clientRepository.save(oldClient);
                })
                .map(newClient -> new ResponseEntity<>(newClient, HttpStatus.OK))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<Client> createClientBusiness(Client client) {
        logger.info("Create business client");
        return clientRepository.save(client);
    }

    /**
     * Search business client by ruc
     * @param ruc
     * @return
     */
    @Override
    public Mono<Client> getClientByRuc(String ruc) {
        logger.info("Get entity business client by RUC");

        if(ruc.isEmpty())
        {
            throw new RuntimeException("RUC is not valid");
        }

        return  clientRepository.
                findAll().
                filter(client -> client.getClientType().equals(Constant.IncomeAccountTypeId.BUSINESS_CREDIT_ID.type) &&
                       client.getBusiness().getRuc().trim().equals(ruc)).singleOrEmpty();
    }

    /**
     * Update old business client with id argument, and client body
     * @param id
     * @param client
     * @return
     */
    @Override
    public Mono<ResponseEntity<Client>> updateNaturalPerson(String id, Client client) {
        logger.info("Update entity person client");
        return clientRepository.findById(id)
                .flatMap(oldClient -> {
                    oldClient.setJoiningDate(client.getJoiningDate());
                    oldClient.setAddress(client.getAddress());
                    oldClient.setClientType(client.getClientType());
                    oldClient.setStatus(client.getStatus());
                    oldClient.setPhones(client.getPhones());
                    oldClient.setEmails(client.getEmails());
                    oldClient.getNaturalPerson().setName(client.getNaturalPerson().getName());
                    oldClient.getNaturalPerson().setLastName(client.getNaturalPerson().getLastName());
                    oldClient.getNaturalPerson().setDocumentType(client.getNaturalPerson().getDocumentType());
                    oldClient.getNaturalPerson().setDocumentNumber(client.getNaturalPerson().getDocumentNumber());
                    return clientRepository.save(oldClient);
                })
                .map(newClient -> new ResponseEntity<>(newClient, HttpStatus.OK))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
