package com.example.smsservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SmsServiceApplication

fun main(args: Array<String>) {
	runApplication<SmsServiceApplication>(*args)
}
