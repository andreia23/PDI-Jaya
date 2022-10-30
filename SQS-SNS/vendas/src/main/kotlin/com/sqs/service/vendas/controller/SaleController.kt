package com.sqs.service.vendas.controller

import com.sqs.service.vendas.model.Saler
import com.sqs.service.vendas.service.SqsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/sale")
class SaleController(
    val sqsService: SqsService
) {

    @PostMapping
    fun newSale(@RequestBody saler: Saler): ResponseEntity<String> {
        sqsService.publishSalerEvent(saler)
        return ResponseEntity("Sale made successfully!", HttpStatus.OK)
    }

}