package com.autoregister.admin.controller

import com.autoregister.naver.api.entity.NaverApi
import com.autoregister.naver.api.service.NaverApiService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class AdminController(
    private val naverApiService: NaverApiService
) {
    @GetMapping
    fun index(): String {
        return "Hello Admin Application"
    }

    @GetMapping("/test")
    fun test(): NaverApi {
        val naverApi = NaverApi()
        return naverApiService.save(naverApi)
    }
}