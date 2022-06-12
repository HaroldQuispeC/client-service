package com.bootcamp.clientservice.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Business {

    private String idBusiness;
    private String businessName;
    private String ruc;
    private ArrayList<NaturalPerson> holders;
    private ArrayList<NaturalPerson> signers;
}
