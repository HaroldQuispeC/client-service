package com.bootcamp.clientservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NaturalPerson {

    private String idClient;
    private String name;
    private String lastName;
    private String documentType;
    private String documentNumber;
    private String gender;
    private boolean active;

}
