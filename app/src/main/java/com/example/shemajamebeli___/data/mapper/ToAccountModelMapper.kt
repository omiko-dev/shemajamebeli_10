package com.example.shemajamebeli___.data.mapper

import com.example.shemajamebeli___.data.model.ToAccountDto
import com.example.shemajamebeli___.domain.model.ToAccountModel

fun ToAccountDto.toDomain(): ToAccountModel =
    ToAccountModel(
        id = id,
        accountName = accountName,
        accountNumber = accountNumber,
        valuteType = valuteType,
        cardType = cardType,
        cardLogo = cardLogo
    )