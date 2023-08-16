package com.autoregister.naver.api.template.common.interfaces

import com.autoregister.naver.api.template.enumaration.ServiceType
import org.springframework.http.HttpMethod

interface NaverCommerceRequest<T> {
    val endpoint: String
    val method: HttpMethod
    val serviceType: ServiceType
    val body: T?
        get() = null
}