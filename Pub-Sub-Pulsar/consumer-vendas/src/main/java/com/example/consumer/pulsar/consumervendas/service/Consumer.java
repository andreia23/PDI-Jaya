package com.example.consumer.pulsar.consumervendas.service;

import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.SubscriptionType;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    private String serviceUrl = "pulsar://localhost:6650";
    private String topicName = "vendas-topic";
    private org.apache.pulsar.client.api.Consumer<byte[]> mConsumer;
    private SaleListener mListener;

    private PulsarClient initClient() throws PulsarClientException {
        return PulsarClient.builder()
                .serviceUrl(serviceUrl)
                .build();
    }

    public void receiveMessage(){
        try {
            run();
            close();
        } catch (PulsarClientException e) {
            throw new RuntimeException(e);
        }
    }

    void run() throws PulsarClientException {
        assert mListener == null;

        mConsumer = initClient()
                .newConsumer()
                .topic(topicName)
                .subscriptionType(SubscriptionType.Shared)
                .subscriptionName(topicName)
                .messageListener(this::readMessage)
                .subscribe();
    }

    private void readMessage(org.apache.pulsar.client.api.Consumer<byte[]> consumer, Message msg){
        try {
            consumer.acknowledge(msg);
            String content = new String(msg.getData());
            mListener.messageFetched(content);
        } catch (PulsarClientException e) {
             return;
        }
    }

    void close() throws PulsarClientException {
        mConsumer.close();
    }
}
