package com.autoregister.naver.api.template.response

import com.fasterxml.jackson.annotation.JsonProperty

data class TokenResponse(
    @JsonProperty("access_token")
    val accessToken: String? = null,
    @JsonProperty("expires_in")
    val expiresIn: Int? = null,
    @JsonProperty("token_type")
    val tokenType: String? = null
) {
    fun validatedAccessToken(): String {
        if (accessToken.isNullOrEmpty()) {
            throw IllegalStateException("access token is null")
        }
        return this.accessToken
    }
}

