package com.sqs.service.vendas.model

import java.math.BigDecimal

data class Saler(
    val value: BigDecimal,

    val idStore: String,

    val products: List<String>,

    val adress: String,

    val idClient: String,

    val idSale: String,

    )
