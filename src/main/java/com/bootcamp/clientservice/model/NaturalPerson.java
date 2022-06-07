package com.bootcamp.clientservice.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NaturalPerson {

    private String idNaturalPerson;
    private String name;
    private String lastName;
    private String documentType;
    private String documentNumber;
    private String gender;
}
