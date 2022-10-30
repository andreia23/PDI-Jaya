package com.example.smsservice.sns

import com.amazonaws.services.sns.AmazonSNS
import com.amazonaws.services.sns.model.Topic
import com.example.smsservice.sqs.MessageSQS
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service


@Service
class ServiceSNS(
    var snsClient: AmazonSNS,
    @Qualifier("vendasTopico")
    var vendasTopico: Topic
) {

    val mapper: ObjectMapper = ObjectMapper()

    fun sendSms(messageSQS: MessageSQS) {
        try {
            val message =
                "Olá, a compra no valor de " + messageSQS.value + "na loja " + messageSQS.idStore + "foi confirmada! Os produtos foram " + messageSQS.products
            snsClient.publish(
                vendasTopico.getTopicArn(),
                message
            )
        } catch (e: Exception) {
            throw RuntimeException("Erro ao processar venda")
        }
    }
}