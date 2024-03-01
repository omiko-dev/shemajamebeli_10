package com.example.shemajamebeli___.presentation.screen.transaction.dialog.to_account_bottom_sheet.event

sealed class ToAccountEvent {
    data class GetToAccountByNumber(val accountNum: String): ToAccountEvent()
}