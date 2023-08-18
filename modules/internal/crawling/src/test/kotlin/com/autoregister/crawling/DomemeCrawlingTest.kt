package com.autoregister.crawling

import com.autoregister.crawling.enumeration.CrawlingType
import com.autoregister.crawling.response.domeme.DomemeResponse
import org.junit.jupiter.api.Test
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.springframework.core.io.ClassPathResource


class DomemeCrawlingTest {
    @Test
    fun crawlingTest() {
        val webDriverID = "webdriver.chrome.driver"
        val webDriverPath =  ClassPathResource("web-driver/chromedriver").file.path

        System.setProperty(webDriverID, webDriverPath)

        //크롬 드라이버 옵션 설정하기
        val options: ChromeOptions = ChromeOptions()
        options.setBinary("")
        options.addArguments("--start-maximized")
        options.addArguments("--disable-popup-blocking")
        options.addArguments("--disable-default-apps")

        val chromeDriver = ChromeDriver(options)

        val crawling: Crawling<DomemeResponse> = DomemeCrawling(chromeDriver)
        crawling.crawl("자전거", DomemeResponse::class.java, CrawlingType.DOMEME)
    }
}