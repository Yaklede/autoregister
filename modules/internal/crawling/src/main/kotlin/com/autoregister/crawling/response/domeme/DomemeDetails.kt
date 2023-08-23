package com.autoregister.crawling.response.domeme

data class DomemeDetails(
    val keyword: String,
    val productName: String,
    val productId: String,
    val productMainImages: List<String>,
    val productDetailsImage: String,
    val amount: Int,
    val deliveryInfo: DeliveryInfo
) {
    data class DeliveryInfo(
        val defaultDeliveryDay: Int,
        val averageShippingDays: Double,
        val deliveryCost: Int,
        val bundledDelivery: Boolean
    )
}
