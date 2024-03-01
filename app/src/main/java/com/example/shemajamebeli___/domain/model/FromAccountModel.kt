package com.example.shemajamebeli___.domain.model

data class FromAccountModel (
    val id: Int,
    val accountName: String,
    val accountNumber: String,
    val valuteType: String,
    val cardType: String,
    val balance: Int,
    val cardLogo: String?,
)