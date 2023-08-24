package com.autoregister.crawling

import com.autoregister.crawling.enumeration.CrawlingType
import com.autoregister.crawling.exception.CrawlingException
import com.autoregister.crawling.response.domeme.DeliveryInfo
import com.autoregister.crawling.response.domeme.DomemeDetails
import com.autoregister.crawling.response.domeme.DomemeResponse
import com.autoregister.crawling.response.domeme.MadeCountry
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.springframework.stereotype.Component

@Component
class DomemeCrawling(
    private val chromeDriver: ChromeDriver
) : Crawling<DomemeResponse> {

    private val numberRegex = Regex("[^0-9]")

    override fun crawl(query: String, returns: Class<DomemeResponse>, type: CrawlingType): DomemeResponse? {
        val url = type.defaultPath + query
        val containerPath = "/html/body/div[1]/div[2]"
        try {
            val count = getTotalCount(url, containerPath)
            println("count = $count")
            val productIds = getProductIds(query, containerPath, count)
            println("productIds size = ${productIds.size}")
            val productDetails = productIds.map { getProductDetails(it) }
            val test = StringBuilder()
            productDetails.forEach {
                test.append(it).append("\n")
            }
            println(test.toString())
        } catch (e: CrawlingException) {
            throw e
        } finally {
            chromeDriver.close()
        }
        return null
    }

    private fun getProductDetails(productId: String): String {
        val url = "http://domeme.domeggook.com/s/$productId"
        val defaultBodyPath = "/html/body/div[5]/div[1]/div[2]"
        val mainPath = "$defaultBodyPath/div[1]"
        val detailPath = "$defaultBodyPath/div[2]"
        try {
            chromeDriver.get(url)

            val main = chromeDriver.findElement(
                By.xpath(mainPath)
            )
            val mainImage = chromeDriver.findElement(
                By.xpath("$mainPath/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/a[1]/img")
            ).getAttribute("src")

            val mainDetailPath = "$mainPath/div[2]/div[2]/div[2]"
            val mainDetailInfoPath = "$mainDetailPath/div[2]/table[1]/tbody[1]"
            val mainTitle = chromeDriver.findElement(
                By.xpath("$mainDetailPath/h1")
            ).text

            val mainInfoPrice = chromeDriver.findElement(
                By.xpath("$mainDetailInfoPath/tr[1]")
            ).text

            var defaultInfoPath = 2
            var domechukPrice = ""

            var mainInfoDeliveryInfo = chromeDriver.findElement(
                By.xpath("$mainDetailInfoPath/tr[$defaultInfoPath]")
            ).text

            if (mainInfoDeliveryInfo.contains("도매꾹")) {
                domechukPrice = mainInfoDeliveryInfo
                defaultInfoPath++
                mainInfoDeliveryInfo = chromeDriver.findElement(
                    By.xpath("$mainDetailInfoPath/tr[$defaultInfoPath]")
                ).text
            }

            val quantity = chromeDriver.findElement(
                By.xpath("$mainDetailInfoPath/tr[${defaultInfoPath + 1}]")
            ).text.replace(numberRegex, "").toInt()

            val madeCountry = chromeDriver.findElement(
                By.xpath("$mainDetailInfoPath/tr[${defaultInfoPath + 2}]")
            ).text

            println("img  = $mainImage")
            println("main title = $mainTitle")
            println("mainInfoPrice = $mainInfoPrice")
            println("mainInfoDeliveryInfo = $mainInfoDeliveryInfo")
            println("domechukPrice = $domechukPrice")
            println("quantity = $quantity")
            println(MadeCountry.parse(madeCountry))
            println(DeliveryInfo.parse(mainInfoDeliveryInfo))


            val details = DomemeDetails(
                productId = productId,
                productName = mainTitle,
                productMainImageUrl = mainImage,
                price = mainInfoPrice,
                domecukPrice = domechukPrice,
                quantity = quantity,
                deliveryInfo = DeliveryInfo.parse(mainInfoDeliveryInfo),
                madeCountry = MadeCountry.parse(madeCountry),
                productDetailsImageUrl = ""
            )

            println(details)

            return ""
        } catch (e: Exception) {
            e.printStackTrace()
            throw CrawlingException("상품 정보 크롤링 중 에러발생")
        }
    }

    private fun getTotalCount(url: String, containerPath: String): Int {
        try {
            chromeDriver.get(url)
            val doc = chromeDriver.findElement(
                By.xpath("$containerPath/div[8]/div[2]/strong")
            )
            return doc.text.replace(numberRegex, "").toInt()
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
            var index = 0
            while (totalCount != productIds.size) {
                val productId = getProductId(containerPath, index)
                if (productId.isNotBlank()) {
                    productIds.add(productId)
                    println("productId = $productId")
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
//                By.xpath("$containerPath/div[$i]/div[4]/div[1]/div[4]/span")
                By.xpath("$containerPath/div[$i]/div[4]/div[1]")
            ).text.replace(numberRegex, "").trim()
        } catch (e: Exception) {
            ""
        }
    }
}