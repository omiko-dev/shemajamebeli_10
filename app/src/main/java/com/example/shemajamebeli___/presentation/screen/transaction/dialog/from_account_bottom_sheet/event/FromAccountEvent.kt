package com.example.shemajamebeli___.presentation.screen.transaction.dialog.from_account_bottom_sheet.event

sealed class FromAccountEvent {
    data object GetFromAccountList: FromAccountEvent()
}