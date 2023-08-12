package com.autoregister.naver.api.entity

import jakarta.persistence.*

@Entity
@Table(name = "naver_api")
class NaverApi(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
)