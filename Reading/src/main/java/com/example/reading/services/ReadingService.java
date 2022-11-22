package com.example.reading.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ReadingService {
    @Autowired
    CircuitBreakerFactory circuitBreakerFactory;

    @RequestMapping(value = "/to-read", method = RequestMethod.GET)
    public String toRead(){
        return circuitBreakerFactory.create("recommended").run(
                () -> new RestTemplate().getForObject("http://localhost:8090/recommended", String.class),
                throwable -> "Cloud Native Java (O'Reilly)");
    }
}
