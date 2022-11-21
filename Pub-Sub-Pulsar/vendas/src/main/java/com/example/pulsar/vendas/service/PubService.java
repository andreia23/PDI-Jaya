package com.example.pulsar.vendas.service;

import com.example.pulsar.vendas.event.SaleEvent;
import com.example.pulsar.vendas.model.Saler;
import org.apache.pulsar.client.api.PulsarClientException;

public class PubService {
    public void publishSalerEvent(Saler salerRequest) throws PulsarClientException {
        String serviceUrl = "pulsar://localhost:6650";
        String topicName = "vendas-topic";

        SaleEvent saleEvent = new SaleEvent(salerRequest.getValue(), salerRequest.getIdStore(), salerRequest.getProducts(), salerRequest.getAdrres(), salerRequest.getIdCliente(), salerRequest.getIdSale());
        PulsarProducerService producer = new PulsarProducerService(serviceUrl, topicName);
        producer.init();
        producer.send(saleEvent.toString());
        producer.close();
    }
}
