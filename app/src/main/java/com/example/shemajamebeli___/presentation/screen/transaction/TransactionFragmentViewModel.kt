package com.example.shemajamebeli___.presentation.screen.transaction

import androidx.lifecycle.ViewModel
import com.example.shemajamebeli___.domain.usecase.GetFromAccountList
import com.example.shemajamebeli___.domain.usecase.GetToAccountByNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionFragmentViewModel @Inject constructor(
    private var getFromAccountList: GetFromAccountList,
    private val getToAccountByNumber: GetToAccountByNumber
): ViewModel() {


}