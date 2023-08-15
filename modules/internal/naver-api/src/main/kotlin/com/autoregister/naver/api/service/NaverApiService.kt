package com.autoregister.naver.api.service

import com.autoregister.naver.api.config.NaverApiProperties
import org.springframework.stereotype.Service

@Service
class NaverApiService(
    private val properties: NaverApiProperties,
) {
    fun getProperties(): NaverApiProperties {
        return properties
    }
}