package com.autoregister.naver.api.template

import com.autoregister.naver.api.config.NaverApiAutoConfiguration
import com.autoregister.naver.api.config.NaverApiProperties
import com.autoregister.naver.api.template.request.ProductOrderIdsRequest
import com.autoregister.naver.api.template.response.ProductOrderIdsResponse
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.client.RestTemplate


class NaverTemplateTest {

    val restTemplate = RestTemplate()
    val properties = NaverApiProperties(
        domain = "https://api.commerce.naver.com",
        clientId = "yourTestId",
        secretKey = "yourTestKey"
    )
    @Test
    @DisplayName("real template test")
    fun naverTemplateTest() {
        val naverTemplate = NaverTemplate(
            properties = properties,
            restTemplate = restTemplate
        )

        val request = ProductOrderIdsRequest(
            orderId = "yourTesTOrderId"
        )
        naverTemplate.execute(request, ProductOrderIdsResponse::class)
    }
}