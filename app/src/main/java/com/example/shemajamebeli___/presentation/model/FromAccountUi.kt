package com.example.shemajamebeli___.presentation.model

data class FromAccountUi (
    val id: Int,
    val accountName: String,
    val accountNumber: String,
    val valuteType: String,
    val cardType: String,
    val balance: Int,
    val cardLogo: String?,
)