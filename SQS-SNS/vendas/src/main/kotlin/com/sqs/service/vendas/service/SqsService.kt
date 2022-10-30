package com.sqs.service.vendas.service

import com.amazonaws.services.sqs.AmazonSQS
import com.fasterxml.jackson.databind.ObjectMapper
import com.sqs.service.vendas.event.SaleEvent
import com.sqs.service.vendas.model.Saler
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service


@Service
class SqsService(
    var snsClient: AmazonSQS,
) {
    val mapper: ObjectMapper = ObjectMapper()

    @Value("\${cloud.aws.sqs.queue.vendas.url}")
    private val queueUrl: String? = null

    fun publishSalerEvent(salerRequest: Saler?) {
        try {
            val event = SaleEvent(salerRequest!!.value, salerRequest.idStore, salerRequest.products, salerRequest.adress, salerRequest.idClient, salerRequest.idSale)
            val message = mapper.writeValueAsString(event)
            snsClient.sendMessage(
                queueUrl,
                message
            )
        } catch (e: Exception) {
            throw RuntimeException("Erro ao processar venda")
        }
    }
}