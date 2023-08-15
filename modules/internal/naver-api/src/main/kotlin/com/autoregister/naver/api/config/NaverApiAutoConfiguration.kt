package com.autoregister.naver.api.config

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan

@AutoConfiguration
@ComponentScan(basePackages = ["com.autoregister.naver.api"])
@EnableConfigurationProperties(NaverApiProperties::class)
class NaverApiAutoConfiguration