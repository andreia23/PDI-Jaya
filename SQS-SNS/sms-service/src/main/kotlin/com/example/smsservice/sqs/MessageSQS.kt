package com.example.smsservice.sqs

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class MessageSQS(
    @JsonProperty("value")
    val value: BigDecimal,
    @JsonProperty("idStore")
    val idStore: Long,
    @JsonProperty("products")
    val products: List<String>
)
