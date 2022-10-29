package com.sns.service.vendas.controller

import com.sns.service.vendas.model.Saler
import com.sns.service.vendas.service.SnsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/sale")
class SaleController(
    val snsService: SnsService
) {

    @PostMapping
    fun newSale(@RequestBody saler: Saler): ResponseEntity<String> {
        snsService.publishSalerEvent(saler)
        return ResponseEntity("Sale made successfully!", HttpStatus.OK)
    }

}