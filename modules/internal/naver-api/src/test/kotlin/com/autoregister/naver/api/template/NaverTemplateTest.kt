package com.autoregister.naver.api.template

import com.autoregister.naver.api.config.NaverApiAutoConfiguration
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [NaverApiAutoConfiguration::class])
class NaverTemplateTest @Autowired constructor(
    private val naverTemplate: NaverTemplate
) {
    @Test
    @DisplayName("naver template test")
    fun naverTemplateTest() {
    }
}