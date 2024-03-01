package com.example.shemajamebeli___.domain.model

import com.squareup.moshi.Json

data class ToAccountModel (
    val id: Int,
    val accountName: String,
    val accountNumber: String,
    val valuteType: String,
    val cardType: String,
    val cardLogo: String?,
)