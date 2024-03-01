package com.example.shemajamebeli___.data.mapper

import com.example.shemajamebeli___.data.model.FromAccountDto
import com.example.shemajamebeli___.domain.model.FromAccountModel

fun FromAccountDto.toDomain(): FromAccountModel =
    FromAccountModel(
        id = id,
        accountName = accountName,
        accountNumber = accountNumber,
        valuteType = valuteType,
        cardType = cardType,
        balance = balance,
        cardLogo = cardLogo
    )