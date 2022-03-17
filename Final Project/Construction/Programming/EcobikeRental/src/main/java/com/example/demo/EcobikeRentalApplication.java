package com.example.demo;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.data.jpa.repository.config.*;

@SpringBootApplication
@EnableJpaAuditing
public class EcobikeRentalApplication {
    public static void main (String[] args) {
        SpringApplication.run(EcobikeRentalApplication.class, args);
    }
}
