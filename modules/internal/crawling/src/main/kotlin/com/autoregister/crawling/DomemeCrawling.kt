package com.autoregister.crawling

import com.autoregister.crawling.enumeration.CrawlingType
import com.autoregister.crawling.exception.CrawlingException
import com.autoregister.crawling.response.domeme.DomemeResponse
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.springframework.stereotype.Component

@Component
class DomemeCrawling(
    private val chromeDriver: ChromeDriver
) : Crawling<DomemeResponse> {
    override fun crawl(query: String, returns: Class<DomemeResponse>, type: CrawlingType): DomemeResponse? {
        val url = type.defaultPath + query
        val containerPath = "/html/body/div[1]/div[2]"
        try {
            val count = getTotalCount(url, containerPath)
            val productIds = getProductIds(query, containerPath, count)
        } catch (e: CrawlingException) {
            throw e
        } finally {
            chromeDriver.close()
        }
        return null
    }

    private fun getProductDetails(productId: String): String {
        TODO()
    }

    private fun getTotalCount(url: String, containerPath: String): Int {
        try {
            chromeDriver.get(url)
            val doc = chromeDriver.findElement(
                By.xpath("$containerPath/div[8]/div[2]/strong")
            )
            return doc.text.replace(Regex("[^0-9]"), "").toInt()
        } catch (e: Exception) {
            e.printStackTrace()
            throw CrawlingException("도매매 크롤링 중 에러 발생")
        }
    }

    private fun getProductIds(query: String, containerPath: String, totalCount: Int): List<String> {
        var totalCount = 10
        val url =
            "https://domemedb.domeggook.com/index/item/supplyList.php?pagenum=0&ca=&mode=search&outCateCode=&outSel=&price1=&price2=&tax=&lowCk=0&country=&oversea=&fromOversea=0&sellerRank=&sellerGood=0&adult=&deliPay=&pageLimit=$totalCount&downLimit=1000&startDate=&endDate=&supplyUnit=1&swi=&so=da&sf=subject&sw=$query&pp1=0&pp2=0&pp3=0&fastDe=0&deliPayYn=N&deliPayPrice=&"

        val productIds = emptyList<String>().toMutableList()
        try {
            chromeDriver.get(url)
            var index = 14
            while (totalCount != productIds.size) {
                val productId = getProductId(containerPath, index)
                if (productId.isNotBlank()) {
                    productIds.add(productId)
                }
                index++
            }
            return productIds
        } catch (e: Exception) {
            e.printStackTrace()
            throw CrawlingException("도매매 상품 아이디 크롤링 중 에러 발생")
        }
    }

    private fun getProductId(
        containerPath: String,
        i: Int,
    ): String {
        return try {
            chromeDriver.findElement(
                By.xpath("$containerPath/div[$i]/div[4]/div[1]/div[4]/span")
            ).text
        } catch (e: Exception) {
            ""
        }

    }
}