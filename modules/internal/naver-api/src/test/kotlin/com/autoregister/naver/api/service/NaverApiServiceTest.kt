package com.autoregister.naver.api.service

import com.autoregister.naver.api.config.NaverApiProperties
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest(classes = [NaverApiService::class, NaverApiProperties::class])
class NaverApiServiceTest {
    @Autowired
    private lateinit var naverApiService: NaverApiService
    @Test
    fun test() {
        naverApiService.getProperties();
    }
}