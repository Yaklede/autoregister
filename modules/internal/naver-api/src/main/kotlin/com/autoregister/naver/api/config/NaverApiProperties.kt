package com.autoregister.naver.api.config

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties(prefix = "naver")
class NaverApiProperties(
    var clientId: String? = null,
    var secretKey: String? = null,
)