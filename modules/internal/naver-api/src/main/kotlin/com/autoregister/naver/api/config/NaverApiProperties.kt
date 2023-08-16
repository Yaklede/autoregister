package com.autoregister.naver.api.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "naver")
class NaverApiProperties(
    var domain: String? = null,
    var clientId: String? = null,
    var secretKey: String? = null,
)