package com.bootcamp.clientservice.model;

import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Client.
 */
@Data
@Getter
@Setter
@Document(collection = "Client")
public class Client {

  @Id
  private String idClient;
  private String clientType;
  private String clientProfile;
  private String joiningDate;
  private String country;
  private String address;
  private String status;
  private List<String> phones;
  private List<String> emails;
  private NaturalPerson naturalPerson;
  private Business business;
}
