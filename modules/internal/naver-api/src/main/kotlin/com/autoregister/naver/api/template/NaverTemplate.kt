package com.autoregister.naver.api.template

import com.autoregister.naver.api.config.NaverApiProperties
import com.autoregister.naver.api.template.common.interfaces.NaverCommerceRequest
import com.autoregister.naver.api.template.common.interfaces.NaverCommerceResponse
import com.autoregister.naver.api.template.crypto.CryptoUtils
import com.autoregister.naver.api.template.exception.FailResponseException
import com.autoregister.naver.api.template.exception.ServiceTypeNotMatchedException
import com.autoregister.naver.api.template.common.dto.TokenResponse
import org.springframework.http.*
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Component
class NaverTemplate(
    private val properties: NaverApiProperties,
    private val restTemplate: RestTemplate
) {

    internal fun <T : NaverCommerceRequest<*>, R : NaverCommerceResponse> execute(request: T, response: Class<R>): R {
        verifyServiceType(request, response)
        val httpEntity = createHttpEntity(request)
        return try {
            restTemplate.exchange("${properties.domain}${request.endpoint}", request.method, httpEntity, response).body!!
        } catch (e: HttpClientErrorException) {
            throw FailResponseException(e.responseBodyAsString)
        } catch (e: HttpServerErrorException) {
            throw FailResponseException(e.responseBodyAsString)
        } catch (e: Exception) {
            e.printStackTrace()
            throw IllegalArgumentException("naver api 호출 중 에러 발생")
        }
    }
    private fun<T: NaverCommerceRequest<*>, R: NaverCommerceResponse> verifyServiceType(request: T, response: Class<R>) {
        val responseType = response.getConstructor().newInstance().serviceType
        if (request.serviceType != responseType) {
            throw ServiceTypeNotMatchedException()
        }
    }

    private fun <T : NaverCommerceRequest<*>> createHttpEntity(request: T): HttpEntity<T> {
        return if (request.body != null) {
            HttpEntity(request, getBaseHeaders())
        } else {
            HttpEntity(getBaseHeaders())
        }
    }

    private fun getBaseHeaders(): HttpHeaders {
        val httpHeaders = HttpHeaders()
        httpHeaders.add("Authorization", "Bearer " + getToken())
        httpHeaders.contentType = MediaType.APPLICATION_JSON
        return httpHeaders
    }


    private fun getToken(): String {
        val timestamp = System.currentTimeMillis()
        val sign: String = CryptoUtils.issueClientSecretSign(properties, timestamp)

        val headers = HttpHeaders()
        val entity = HttpEntity<String>(headers)
        val getTokenUrl = "https://api.commerce.naver.com/external/v1/oauth2/token"

        val build = UriComponentsBuilder.fromHttpUrl(getTokenUrl)
            .queryParam("client_id", properties.clientId)
            .queryParam("timestamp", timestamp)
            .queryParam("client_secret_sign", sign)
            .queryParam("grant_type", "client_credentials")
            .queryParam("type", "SELF")
            .build()

        return try {
            restTemplate.exchange(
                build.toString(), HttpMethod.POST, entity,
                TokenResponse::class.java
            ).let {
                verifyToken(it)
            }
        } catch (e: HttpClientErrorException) {
            throw FailResponseException(e.responseBodyAsString)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    private fun verifyToken(exchange: ResponseEntity<TokenResponse>): String {
        if (exchange.body == null) {
            throw IllegalArgumentException("token 조회에 실패했습니다.")
        }
        return exchange.body!!.validatedAccessToken()
    }
}