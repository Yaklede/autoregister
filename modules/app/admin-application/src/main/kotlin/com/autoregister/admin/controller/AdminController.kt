package com.autoregister.admin.controller

import com.autoregister.naver.api.config.NaverApiProperties
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
    @GetMapping("/properties")
    fun properties(): NaverApiProperties {
        return naverApiService.getProperties()
    }
}