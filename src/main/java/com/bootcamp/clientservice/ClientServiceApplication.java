package com.bootcamp.clientservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * ClientServiceApplication.
 */
@EnableEurekaClient
@SpringBootApplication
public class ClientServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ClientServiceApplication.class, args);
  }

}
