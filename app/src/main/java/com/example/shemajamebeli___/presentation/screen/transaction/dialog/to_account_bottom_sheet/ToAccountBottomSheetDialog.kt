package com.example.shemajamebeli___.presentation.screen.transaction.dialog.to_account_bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.shemajamebeli___.databinding.ToAccountBottomSheetBinding
import com.example.shemajamebeli___.presentation.model.ToAccountUi
import com.example.shemajamebeli___.presentation.screen.transaction.dialog.to_account_bottom_sheet.event.ToAccountEvent
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ToAccountBottomSheetDialog : BottomSheetDialogFragment() {
    private val viewModel: ToAccountViewModel by viewModels()
    private var _binding: ToAccountBottomSheetBinding? = null
    private val binding get() = _binding!!
    private var toAccountListener: ToAccountListener? = null
    private var rbId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = ToAccountBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transactionTypeListener()
        getUserInfoListener()
        toAccountObserve()
    }

    private fun transactionTypeListener() {
        with(binding) {
            rbId = rbAccountNum.id
            rgTransactionType.setOnCheckedChangeListener { _, checkedId ->
                rbId = checkedId

                when (checkedId) {
                    rbAccountNum.id -> {
                        etAccountNum.visibility = View.VISIBLE
                        etMobileNum.visibility = View.GONE
                        etPersonalNum.visibility = View.GONE
                    }

                    rbMobileNum.id -> {
                        etAccountNum.visibility = View.GONE
                        etMobileNum.visibility = View.VISIBLE
                        etPersonalNum.visibility = View.GONE
                    }

                    rbPersonalNum.id -> {
                        etAccountNum.visibility = View.GONE
                        etMobileNum.visibility = View.GONE
                        etPersonalNum.visibility = View.VISIBLE

                    }
                }
            }
        }
    }

    private fun toAccountObserve(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.toAccountStateFlow.collect { state ->
                    state.toAccount?.let {
                        toAccountListener?.getToAccount(it)
                        dismiss()
                    }
                }
            }
        }
    }

    private fun getUserInfoListener() {
        with(binding) {
            btnEnter.setOnClickListener {
                rbId?.let {
                    when (it) {
                        rbAccountNum.id -> {
                            val result = rbAccountNum.text.toString()
                            viewModel.onEvent(ToAccountEvent.GetToAccountByNumber(result))
                        }

                        rbPersonalNum.id -> {
                            val result = rbPersonalNum.text.toString()
                            viewModel.onEvent(ToAccountEvent.GetToAccountByNumber(result))
                        }

                        rbMobileNum.id -> {
                            val result = rbMobileNum.text.toString()
                            viewModel.onEvent(ToAccountEvent.GetToAccountByNumber(result))
                        }
                    }
                }
            }
        }
    }

    interface ToAccountListener {
        fun getToAccount(toAccountUi: ToAccountUi)
    }

    fun setToAccount(listener: ToAccountListener) {
        toAccountListener = listener
    }
}