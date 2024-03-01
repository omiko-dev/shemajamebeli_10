package com.example.shemajamebeli___.presentation.screen.transaction.event

import com.example.shemajamebeli___.presentation.model.FromAccountUi
import com.example.shemajamebeli___.presentation.model.ToAccountUi

sealed class TransactionEvent {
    data class UpdateFromAccount(val fromAccountUi: FromAccountUi): TransactionEvent()
    data class UpdateToAccount(val toAccountUi: ToAccountUi): TransactionEvent()
}