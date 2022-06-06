package com.bootcamp.clientservice.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@Document(collection = "Client")
public class Client {

    @Id
    private String idClient;
    private String joiningDate;
    private String country;
    private String address;
    private String clientType;

    private NaturalPerson naturalPerson;

    private Business business;
}
