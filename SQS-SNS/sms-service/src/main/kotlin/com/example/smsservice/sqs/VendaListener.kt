package com.example.smsservice.sqs

import com.example.smsservice.sns.ServiceSNS
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.stereotype.Component

@Component
class VendaListener(
    val serviceSNS: ServiceSNS
) {
    val mapper: ObjectMapper = ObjectMapper()

    @SqsListener(
        value = arrayOf("https://sqs.sa-east-1.amazonaws.com/556453201432/vendas-queue"),
        deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS
    )
    fun recieveMessage(record: String?) {
        try {
            val messageSQS = mapper.readValue(record, MessageSQS::class.java)
            serviceSNS.sendSms(messageSQS)
        } catch (e: Exception) {
            throw RuntimeException("Erro ao receber mensagem da fila")
        }
    }
}