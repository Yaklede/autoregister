package com.autoregister.naver.api.template.common.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class FailResponse(
    @JsonProperty("code")
    val code: String,
    @JsonProperty("message")
    val message: String,
    @JsonProperty("timestamp")
    val timestamp: String,
    @JsonProperty("traceId")
    val traceId: String,
    @JsonProperty("invalidInputs")
    val invalidInputs: List<InvalidInput>
)