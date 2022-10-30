package com.example.smsservice.config
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.sns.AmazonSNS
import com.amazonaws.services.sns.AmazonSNSClientBuilder
import com.amazonaws.services.sns.model.Topic
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary


@Configuration
class AwsConfig {
    @Value("\${cloud.aws.credentials.access-key}")
    private val awsAccessKey: String? = null

    @Value("\${cloud.aws.credentials.secret-key}")
    private val awsSecretKey: String? = null

    @Value("\${cloud.aws.topic.arn}")
    private val topic: String? = null

    @Bean
    @Primary
    fun snsClient(): AmazonSNS? {
        return AmazonSNSClientBuilder.standard()
            .withRegion(Regions.SA_EAST_1)
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(awsAccessKey, awsSecretKey)))
            .build()
    }

    @Bean(name = arrayOf("vendasTopico"))
    fun snsVendasTopico(): Topic? {
        return Topic().withTopicArn(topic)
    }
}