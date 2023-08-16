package com.autoregister.naver.api.template.crypto

import com.autoregister.naver.api.config.NaverApiProperties
import org.apache.commons.lang3.StringUtils
import org.mindrot.jbcrypt.BCrypt
import java.nio.charset.StandardCharsets
import java.util.*

object CryptoUtils {
    fun issueClientSecretSign(properties: NaverApiProperties, timestamp: Long): String {
        val password = StringUtils.joinWith("_", properties.clientId, timestamp)
        val hashedPassword = BCrypt.hashpw(password, properties.secretKey)
        return Base64.getUrlEncoder().encodeToString(hashedPassword.toByteArray(StandardCharsets.UTF_8))
    }
}