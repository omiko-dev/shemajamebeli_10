package com.example.shemajamebeli___.presentation.state

import com.example.shemajamebeli___.presentation.model.ToAccountUi

data class ToAccountState (
    var toAccount: ToAccountUi? = null,
    var error: String? = null,
    var loader: Boolean = false
)