package com.example.shemajamebeli___.presentation.screen.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shemajamebeli___.presentation.model.FromAccountUi
import com.example.shemajamebeli___.presentation.model.ToAccountUi
import com.example.shemajamebeli___.presentation.screen.transaction.event.TransactionEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionFragmentViewModel @Inject constructor() : ViewModel() {
    private val _fromAccountStateFlow = MutableStateFlow<FromAccountUi?>(null)
    val fromAccountStateFlow get() = _fromAccountStateFlow.asStateFlow()

    private val _toAccountStateFlow = MutableStateFlow<ToAccountUi?>(null)
    val toAccountStateFlow get() = _toAccountStateFlow.asStateFlow()

    fun onEvent(event: TransactionEvent) {
        when (event) {
            is TransactionEvent.UpdateFromAccount -> updateFromAccount(event.fromAccountUi)
            is TransactionEvent.UpdateToAccount -> updateToAccount(event.toAccountUi)
        }
    }

    private fun updateFromAccount(fromAccountUi: FromAccountUi) {
        viewModelScope.launch {
            _fromAccountStateFlow.value = fromAccountUi
        }
    }

    private fun updateToAccount(toAccountUi: ToAccountUi) {
        viewModelScope.launch {
            _toAccountStateFlow.value = toAccountUi
        }
    }
}





