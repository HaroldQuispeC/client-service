package com.bootcamp.clientservice.service.impl;

import com.bootcamp.clientservice.model.Business;
import com.bootcamp.clientservice.model.Client;
import com.bootcamp.clientservice.model.NaturalPerson;
import com.bootcamp.clientservice.repository.ClientRepository;
import com.bootcamp.clientservice.service.ClientService;
import com.bootcamp.clientservice.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * ClientServiceImpl.
 */
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

  /**
   * getClientByDocumentNumber.
   *
   * @param dni String
   * @return Mono Client
   */
  @Override
  public Mono<Client> getClientByDocumentNumber(String dni) {
    if (dni.isEmpty()) {
      throw new RuntimeException("DNI is not valid");
    }

    logger.info("Get entity client by Document Number");
    Mono<Client> clientNaturalPerson = clientRepository.findAll()
            .filter(client -> client.getClientType().equals(Constants.NATURAL_PERSON_CLIENT))
            .filter(c -> c.getNaturalPerson().getDocumentNumber().equals(dni)).take(1).single()
            .onErrorResume(error -> {
              logger.error("No se encuentra registrado el DNI" + dni, error.getMessage());
              return Mono.empty();
            });
    return clientNaturalPerson;
  }

  /**
   * updateNaturalPerson.
   *
   * @param dni    String
   * @param client Client
   * @return Mono Client
   */
  @Override
  public Mono<Client> updateNaturalPerson(String dni, Client client) {
    logger.info("Update NaturalPerson entity by dni");
    Mono<Client> newClient = getClientByDocumentNumber(dni)
            .flatMap(c -> {
              c.setAddress(client.getAddress());
              c.setCountry(client.getCountry());

              NaturalPerson n = client.getNaturalPerson();
              n.setName(client.getNaturalPerson().getName());
              n.setLastName(client.getNaturalPerson().getLastName());
              n.setDocumentNumber(client.getNaturalPerson().getDocumentNumber());
              n.setGender(client.getNaturalPerson().getGender());
              c.setNaturalPerson(n);
              return clientRepository.save(c);
            });
    return newClient;
  }

  @Override
  public Mono<Client> createClientBusiness(Client client) {
    logger.info("Create business client");
    return clientRepository.save(client);
  }

  @Override
  public Mono<Client> getClientByRuc(String ruc) {
    if (ruc.isEmpty()) {
      throw new RuntimeException("RUC is not valid");
    }
    logger.info("Get entity business client by RUC");
    Mono<Client> clientsBusiness = clientRepository.findAll()
            .filter(c -> c.getClientType().equals(Constants.BUSINESS_CLIENT))
            .filter(b -> b.getBusiness().getRuc().equals(ruc)).take(1).single()
            .onErrorResume(error -> {
              logger.error("No se encuentra registrado el RUC" + ruc, error.getMessage());
              return Mono.empty();
            });
    return clientsBusiness;
  }

  /**
   * updateBusiness.
   *
   * @param ruc    String
   * @param client Client
   * @return Mono Client
   */

  @Override
  public Mono<Client> updateBusiness(String ruc, Client client) {
    logger.info("Update business client entity by id");
    Mono<Client> newBusiness = getClientByRuc(ruc)
            .flatMap(business -> {
              business.setAddress(client.getAddress());
              business.setCountry(client.getCountry());

              Business b = business.getBusiness();
              b.setBusinessName(client.getBusiness().getBusinessName());
              b.setRuc(client.getBusiness().getRuc());
              return clientRepository.save(business);
            });
    return newBusiness;
  }

}
