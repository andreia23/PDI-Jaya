package com.example.consumer.pulsar.consumervendas.service;

import org.apache.pulsar.client.api.PulsarClientException;

public interface SaleListener {
    void messageFetched(String msg) throws PulsarClientException;
}
