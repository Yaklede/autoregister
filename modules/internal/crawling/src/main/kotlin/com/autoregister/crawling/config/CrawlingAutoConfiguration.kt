package com.autoregister.crawling.config

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.core.io.ClassPathResource
import org.springframework.web.client.RestTemplate
import java.time.Duration

@AutoConfiguration
@ComponentScan(basePackages = ["com.autoregister.crawling"])
class CrawlingAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    fun restTemplate(): RestTemplate {
        return RestTemplateBuilder()
            .setConnectTimeout(Duration.ofSeconds(5L))
            .setReadTimeout(Duration.ofSeconds(5L))
            .build()
    }

    @Bean
    fun chromeDriver(): ChromeDriver {
        val webDriverID = "webdriver.chrome.driver"
        val webDriverPath =  ClassPathResource("web-driver/chromedriver").file.path

        System.setProperty(webDriverID, webDriverPath)

        //크롬 드라이버 옵션 설정하기
        val options: ChromeOptions = ChromeOptions()
        options.setBinary("")
        options.addArguments("--start-maximized")
        options.addArguments("--disable-popup-blocking")
        options.addArguments("--disable-default-apps")

        return ChromeDriver(options)
    }
}