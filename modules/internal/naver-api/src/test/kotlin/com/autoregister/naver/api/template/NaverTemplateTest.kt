package com.autoregister.naver.api.template

import com.autoregister.naver.api.config.NaverApiAutoConfiguration
import com.autoregister.naver.api.config.NaverApiProperties
import com.autoregister.naver.api.template.exception.FailResponseException
import com.autoregister.naver.api.template.request.ProductOrderIdsRequest
import com.autoregister.naver.api.template.response.ProductOrderIdsResponse
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.client.RestTemplate


class NaverTemplateTest {

    val restTemplate = RestTemplate()
    @Test
    @DisplayName("wrong properties")
    fun wrongproperties() {
        val properties = NaverApiProperties(
            domain = "https://api.commerce.naver.com",
            clientId = "clientId",
            secretKey = "secretKey"
        )

        val naverTemplate = NaverTemplate(
            properties = properties,
            restTemplate = restTemplate
        )

        val request = ProductOrderIdsRequest(
            orderId = "tes"
        )

        Assertions.assertThatThrownBy {
            naverTemplate.execute(request, ProductOrderIdsResponse::class)
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    @DisplayName("read template")
    fun naverTemplateTest() {
        val properties = NaverApiProperties(
            domain = "https://api.commerce.naver.com",
            clientId = "readId",
            secretKey = "realKey"
        )

        val naverTemplate = NaverTemplate(
            properties = properties,
            restTemplate = restTemplate
        )

        val request = ProductOrderIdsRequest(
            orderId = "tes"
        )

        Assertions.assertThatThrownBy {
            naverTemplate.execute(request, ProductOrderIdsResponse::class)
        }.isInstanceOf(FailResponseException::class.java)
    }
}