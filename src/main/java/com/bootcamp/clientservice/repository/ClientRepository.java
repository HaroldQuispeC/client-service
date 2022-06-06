package com.bootcamp.clientservice.repository;

import com.bootcamp.clientservice.model.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends ReactiveMongoRepository<Client,String> {
}
