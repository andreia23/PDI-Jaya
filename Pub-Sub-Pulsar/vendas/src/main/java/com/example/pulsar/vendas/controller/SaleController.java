package com.example.pulsar.vendas.controller;

import com.example.pulsar.vendas.model.Saler;
import com.example.pulsar.vendas.service.PubService;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sale")
@SuppressWarnings("unchecked")
public class SaleController {

    @Autowired
    private PubService pubService;

    @PostMapping
    public ResponseEntity<String> newSale (@RequestBody Saler saler) throws PulsarClientException {
        pubService.publishSalerEvent(saler);
        return new ResponseEntity("Sale made successfully!", HttpStatus.OK);
    }
}
