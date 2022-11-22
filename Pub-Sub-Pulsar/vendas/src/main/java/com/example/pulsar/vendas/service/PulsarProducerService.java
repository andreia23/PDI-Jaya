//package com.example.pulsar.vendas.service;
//
//import org.apache.pulsar.client.api.*;
//import org.springframework.stereotype.Service;
//
//@Service
//public class PulsarProducerService {
//    private final String mServiceUrl;
//    private final String mTopicName;
//    private Producer<byte[]> mProducer;
//
//    PulsarProducerService(String serviceUrl, String topicName) {
//        mServiceUrl = serviceUrl;
//        mTopicName = topicName;
//    }
//
//    void init() throws PulsarClientException {
//
//
//        mProducer = initClient()
//                .newProducer()
//                .topic(mTopicName)
//                .create();
//    }
//
//    void send(String msg) {
//
//        try {
//            byte[] msgBytes = msg.getBytes();
//            MessageId msgId = mProducer.send(msgBytes);
//        } catch (PulsarClientException e) {
//
//        }
//    }
//
//    void close() throws PulsarClientException {
//        mProducer.close();
//    }
//
//    private PulsarClient initClient() throws PulsarClientException {
//        return PulsarClient.builder()
//                .serviceUrl(mServiceUrl)
//                .build();
//    }
//}
