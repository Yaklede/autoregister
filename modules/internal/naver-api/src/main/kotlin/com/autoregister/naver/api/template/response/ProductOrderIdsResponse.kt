package com.autoregister.naver.api.template.response

import com.autoregister.naver.api.template.common.interfaces.NaverCommerceResponse
import com.autoregister.naver.api.template.enumaration.ServiceType
import com.fasterxml.jackson.annotation.JsonProperty

data class ProductOrderIdsResponse(
    @JsonProperty("timestamp")
    var timestamp: String? = null,
    @JsonProperty("traceId")
    var traceId: String? = null,
    @JsonProperty("data")
    var productOrderIds: List<String>? = null
) : NaverCommerceResponse {
    override val serviceType: ServiceType = ServiceType.PRODUCT_ORDER_IDS
}