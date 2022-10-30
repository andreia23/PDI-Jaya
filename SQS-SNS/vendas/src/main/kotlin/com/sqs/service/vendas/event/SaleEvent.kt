package com.sqs.service.vendas.event

import java.math.BigDecimal

data class SaleEvent(
    val value: BigDecimal,

    val idStore: String,

    val products: List<String>,

    val adress: String,

    val idClient: String,

    val idSale: String,
)
