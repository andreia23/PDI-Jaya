package com.example.pulsar.vendas.service;

import com.example.pulsar.vendas.event.SaleEvent;
import com.example.pulsar.vendas.model.Saler;
import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("unchecked")
public class PubService {

    private final String serviceUrl = "pulsar://localhost:6650";
    private final String topicName = "vendas-topic";
    private Producer<byte[]> mProducer;

    public void publishSalerEvent(Saler salerRequest) throws PulsarClientException {
        SaleEvent saleEvent = new SaleEvent(salerRequest.getValue(), salerRequest.getIdStore(), salerRequest.getProducts(), salerRequest.getAdrres(), salerRequest.getIdCliente(), salerRequest.getIdSale());
        Producer<byte[]> producer = initClient().newProducer()
                .topic(topicName)
                .create();
        send(saleEvent.toString());
        mProducer.close();
        initClient().close();

    }

    public void send(String msg) throws PulsarClientException {

        try {
            byte[] msgBytes = msg.getBytes();
            MessageId msgId = mProducer.send(msgBytes);
        } catch (PulsarClientException e) {
            throw e;
        }
    }

    PulsarClient initClient() throws PulsarClientException {
        return PulsarClient.builder()
                .serviceUrl(serviceUrl)
                .build();
    }
}
