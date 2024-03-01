package com.example.shemajamebeli___.presentation.screen.transaction.dialog.from_account_bottom_sheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shemajamebeli___.data.common.Resource
import com.example.shemajamebeli___.domain.usecase.GetFromAccountList
import com.example.shemajamebeli___.presentation.mapper.toPresenter
import com.example.shemajamebeli___.presentation.screen.transaction.dialog.from_account_bottom_sheet.event.FromAccountEvent
import com.example.shemajamebeli___.presentation.state.FromAccountState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class FromAccountViewModel @Inject constructor(
    private val getFromAccountList: GetFromAccountList,
) : ViewModel() {
    private val _fromAccountList = MutableStateFlow(FromAccountState())
    val fromAccountList get() = _fromAccountList.asStateFlow()

    fun onEvent(event: FromAccountEvent) {
        when (event) {
            is FromAccountEvent.GetFromAccountList -> getFromAccounts()
        }
    }

    private fun getFromAccounts() {
        viewModelScope.launch {
            getFromAccountList().collect { resource ->
                when(resource){
                    is Resource.Success -> {
                        _fromAccountList.update { state ->
                            state.copy(
                                fromAccount = resource.success.map { it.toPresenter() }
                            )
                        }
                    }
                    is Resource.Error -> {
                        _fromAccountList.update { state ->
                            state.copy(
                                error = resource.error
                            )
                        }
                    }
                    is Resource.Loader -> {
                        _fromAccountList.update { state ->
                            state.copy(
                                loader = resource.loader
                            )
                        }
                    }
                }
            }
        }
    }
}