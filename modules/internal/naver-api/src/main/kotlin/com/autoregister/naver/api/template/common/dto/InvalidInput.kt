package com.autoregister.naver.api.template.common.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class InvalidInput(
    @JsonProperty("name")
    val name: String,
    @JsonProperty("type")
    val type: String,
    @JsonProperty("message")
    val message: String
)
