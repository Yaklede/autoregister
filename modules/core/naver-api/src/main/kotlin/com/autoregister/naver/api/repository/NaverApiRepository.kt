package com.autoregister.naver.api.repository

import com.autoregister.naver.api.entity.NaverApi
import org.springframework.data.jpa.repository.JpaRepository

interface NaverApiRepository : JpaRepository<NaverApi, Long> {
}