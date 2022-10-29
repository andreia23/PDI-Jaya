package com.sns.service.vendas.service

import com.amazonaws.services.sns.AmazonSNS
import com.amazonaws.services.sns.model.PublishResult
import com.amazonaws.services.sns.model.Topic
import com.fasterxml.jackson.databind.ObjectMapper
import com.sns.service.vendas.event.SaleEvent
import com.sns.service.vendas.model.Saler
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service


@Service
class SnsService(
    var snsClient: AmazonSNS,
    @Qualifier("vendasTopico")
    var vendasTopico: Topic
) {

    val mapper: ObjectMapper = ObjectMapper()

    fun publishSalerEvent(salerRequest: Saler?) {
        try {
            val event = SaleEvent(salerRequest!!.value, salerRequest.idStore, salerRequest.products, salerRequest.adress, salerRequest.idClient, salerRequest.idSale)
            val message = mapper.writeValueAsString(event)
            val publishResult = snsClient.publish(
                vendasTopico.getTopicArn(),
                message
            )
        } catch (e: Exception) {
            throw RuntimeException("Erro ao processar venda")
        }
    }
}