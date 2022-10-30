package com.sqs.service.vendas.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.AmazonSQSClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class AwsSqsConfig {
    @Value("\${cloud.aws.credentials.access-key}")
    private val awsAccessKey: String? = null

    @Value("\${cloud.aws.credentials.secret-key}")
    private val awsSecretKey: String? = null

    @Bean
    fun sqsClient(): AmazonSQS? {
        return AmazonSQSClientBuilder.standard()
            .withRegion(Regions.SA_EAST_1)
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(awsAccessKey, awsSecretKey)))
            .build()
    }
}