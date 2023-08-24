package com.autoregister.crawling.response.domeme

import com.autoregister.crawling.exception.CrawlingException
import com.autoregister.crawling.response.domeme.enumeration.CountryType
import java.util.regex.Pattern

data class DomemeDetails(
    val productId: String,
    val productName: String,
    val productMainImageUrl: String,
    val productDetailsImageUrl: String,
    val price: String,
    val domecukPrice: String,
    val quantity: Int,
    val deliveryInfo: DeliveryInfo,
    val madeCountry: MadeCountry
)
