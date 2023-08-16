package com.autoregister.naver.api.template.request

import com.autoregister.naver.api.template.common.interfaces.NaverCommerceRequest
import com.autoregister.naver.api.template.enumaration.ServiceType
import org.springframework.http.HttpMethod

data class ProductOrderIdsRequest(
    val orderId: String = ""
) : NaverCommerceRequest<Any> {
    override val endpoint: String = "/external/v1/pay-order/seller/orders/$orderId/product-order-ids"
    override val method: HttpMethod = HttpMethod.GET
    override val serviceType: ServiceType = ServiceType.PRODUCT_ORDER_IDS
}