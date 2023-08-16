package com.autoregister.naver.api.config

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [TestConfig::class], initializers = [ConfigDataApplicationContextInitializer::class])
class NaverApiPropertiesTest @Autowired constructor(
    private val naverApiProperties: NaverApiProperties
) {
    @Test
    @DisplayName("properties load test")
    fun propertiesLoad() {
        Assertions.assertThat(naverApiProperties.domain).isEqualTo("testDomain")
        Assertions.assertThat(naverApiProperties.clientId).isEqualTo("testId")
        Assertions.assertThat(naverApiProperties.secretKey).isEqualTo("testKey")
    }
}

@TestConfiguration
@EnableConfigurationProperties(NaverApiProperties::class)
class TestConfig