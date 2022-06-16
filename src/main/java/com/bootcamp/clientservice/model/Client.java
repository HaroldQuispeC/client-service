package com.bootcamp.clientservice.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@Document(collection = "Client")
public class Client {

    @Id
    private String idClient;
    private Date joiningDate;
    private String country;
    private String address;
    private ClientType clientType;
    private String status;
    private List<String> phones;
    private List<String> emails;
    private NaturalPerson naturalPerson;
    private Business business;
}
