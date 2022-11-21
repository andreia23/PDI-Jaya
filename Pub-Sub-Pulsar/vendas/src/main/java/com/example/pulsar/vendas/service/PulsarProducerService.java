package com.example.pulsar.vendas.service;

import org.apache.pulsar.client.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PulsarProducerService {
    private final String mServiceUrl;
    private final String mTopicName;
    private Producer<byte[]> mProducer;
    private final Logger mLogger = LoggerFactory.getLogger(PulsarProducerService.class);

    PulsarProducerService(String serviceUrl, String topicName) {
        mServiceUrl = serviceUrl;
        mTopicName = topicName;
    }

    void init() throws PulsarClientException {
        mLogger.info("Instantiating producer...");

        mProducer = initClient()
                .newProducer()
                .topic(mTopicName)
                .compressionType(CompressionType.LZ4)
                .create();
    }

    void send(String msg) {
        mLogger.info("Producer sending message: " + msg);

        try {
            byte[] msgBytes = msg.getBytes();
            MessageId msgId = mProducer.send(msgBytes);
            mLogger.info("Producer sent message ID: " + msgId);
        } catch (PulsarClientException e) {
            mLogger.info("Producer message error: " + e.getMessage());
        }
    }

    void close() throws PulsarClientException {
        mProducer.close();
        mLogger.info("Producer is closed");
    }

    private PulsarClient initClient() throws PulsarClientException {
        return PulsarClient.builder()
                .serviceUrl(mServiceUrl)
                .build();
    }
}
