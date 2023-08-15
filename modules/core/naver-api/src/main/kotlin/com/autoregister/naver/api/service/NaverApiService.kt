package com.autoregister.naver.api.service

import com.autoregister.naver.api.entity.NaverApi
import com.autoregister.naver.api.repository.NaverApiRepository
import org.springframework.stereotype.Service

@Service
class NaverApiService(
    private val naverApiRepository: NaverApiRepository
) {
    fun save(naverApi: NaverApi): NaverApi {
        return naverApiRepository.save(naverApi)
    }
}