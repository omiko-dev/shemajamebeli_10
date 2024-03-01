package com.example.shemajamebeli___.presentation.screen.transaction.dialog.to_account_bottom_sheet

import android.util.Log.i
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shemajamebeli___.data.common.Resource
import com.example.shemajamebeli___.domain.usecase.GetToAccountByNumber
import com.example.shemajamebeli___.presentation.mapper.toPresenter
import com.example.shemajamebeli___.presentation.screen.transaction.dialog.to_account_bottom_sheet.event.ToAccountEvent
import com.example.shemajamebeli___.presentation.state.ToAccountState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToAccountViewModel @Inject constructor(
    private val getToAccountByNumber: GetToAccountByNumber,
) : ViewModel() {

    private val _toAccountStateFlow = MutableStateFlow(ToAccountState())
    val toAccountStateFlow get() = _toAccountStateFlow.asStateFlow()

    fun onEvent(event: ToAccountEvent) {
        when (event) {
            is ToAccountEvent.GetToAccountByNumber -> {
                getToAccount(event.accountNum)
            }
        }
    }

    private fun getToAccount(number: String) {
        viewModelScope.launch {
            getToAccountByNumber(number).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        i("omiko", "in")
                        _toAccountStateFlow.update {
                            it.copy(
                                toAccount = resource.success.toPresenter()
                            )
                        }
                    }

                    is Resource.Loader -> {

                        i("omiko", "in loader")
                        _toAccountStateFlow.update {
                            it.copy(
                                loader = resource.loader
                            )
                        }
                    }

                    is Resource.Error -> {
                        i("omiko", "in error")
                        _toAccountStateFlow.update {
                            it.copy(
                                error = resource.error
                            )
                        }
                    }
                }
            }
        }
    }
}