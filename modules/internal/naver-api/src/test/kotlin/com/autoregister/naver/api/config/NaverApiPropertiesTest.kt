package com.autoregister.naver.api.config

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [NaverApiProperties::class])
class NaverApiPropertiesTest @Autowired constructor(
    private val properties: NaverApiProperties
) {
    @Test
    @DisplayName("properties load test")
    fun propertiesLoadTest() {
        println(properties.clientId)
        println(properties.secretKey)
    }
}