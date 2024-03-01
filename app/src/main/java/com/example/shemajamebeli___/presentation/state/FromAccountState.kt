package com.example.shemajamebeli___.presentation.state

import com.example.shemajamebeli___.presentation.model.FromAccountUi

data class FromAccountState (
    var fromAccount: List<FromAccountUi>? = null,
    var error: String? = null,
    var loader: Boolean = false
)