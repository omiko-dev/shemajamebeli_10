package com.example.shemajamebeli___.presentation.mapper

import com.example.shemajamebeli___.domain.model.FromAccountModel
import com.example.shemajamebeli___.presentation.model.FromAccountUi

fun FromAccountModel.toPresenter(): FromAccountUi =
    FromAccountUi(
        id = id,
        accountName = accountName,
        accountNumber = accountNumber,
        valuteType = valuteType,
        cardType = cardType,
        balance = balance,
        cardLogo = cardLogo
    )