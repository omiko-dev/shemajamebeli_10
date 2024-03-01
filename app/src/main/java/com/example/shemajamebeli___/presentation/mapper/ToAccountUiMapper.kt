package com.example.shemajamebeli___.presentation.mapper

import com.example.shemajamebeli___.domain.model.ToAccountModel
import com.example.shemajamebeli___.presentation.model.ToAccountUi

fun ToAccountModel.toPresenter(): ToAccountUi =
    ToAccountUi(
        id = id,
        accountName = accountName,
        accountNumber = accountNumber,
        valuteType = valuteType,
        cardType = cardType,
        cardLogo = cardLogo
    )