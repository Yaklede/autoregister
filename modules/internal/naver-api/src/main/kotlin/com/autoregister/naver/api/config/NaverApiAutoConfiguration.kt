package com.autoregister.naver.api.config

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.client.RestTemplate
import java.time.Duration

@AutoConfiguration
@ComponentScan(basePackages = ["com.autoregister.naver.api"])
@EnableConfigurationProperties(NaverApiProperties::class)
class NaverApiAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(RestTemplate::class)
    fun restTemplate(): RestTemplate {
        val restTemplateBuilder = RestTemplateBuilder()
        restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(10L))
        restTemplateBuilder.setReadTimeout(Duration.ofSeconds(10L))
        return restTemplateBuilder.build()
    }
}