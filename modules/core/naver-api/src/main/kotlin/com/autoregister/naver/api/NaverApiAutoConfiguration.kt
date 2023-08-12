package com.autoregister.naver.api

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@AutoConfiguration
@EntityScan(basePackages = ["com.autoregister.naver.api.entity"])
@EnableJpaRepositories(basePackages = ["com.autoregister.naver.api.repository"])
@ComponentScan(basePackages = ["com.autoregister.naver.api"])
class NaverApiAutoConfiguration {
}