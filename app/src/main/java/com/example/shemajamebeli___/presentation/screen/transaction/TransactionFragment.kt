package com.example.shemajamebeli___.presentation.screen.transaction


import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.shemajamebeli___.databinding.FragmentTransactionBinding
import com.example.shemajamebeli___.presentation.base.BaseFragment
import com.example.shemajamebeli___.presentation.model.FromAccountUi
import com.example.shemajamebeli___.presentation.model.ToAccountUi
import com.example.shemajamebeli___.presentation.screen.transaction.dialog.from_account_bottom_sheet.FromAccountBottomSheetDialog
import com.example.shemajamebeli___.presentation.screen.transaction.dialog.to_account_bottom_sheet.ToAccountBottomSheetDialog
import com.example.shemajamebeli___.presentation.screen.transaction.event.TransactionEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TransactionFragment :
    BaseFragment<FragmentTransactionBinding>(FragmentTransactionBinding::inflate),
    ToAccountBottomSheetDialog.ToAccountListener, FromAccountBottomSheetDialog.FromAccountListener {
    private val viewModel: TransactionFragmentViewModel by viewModels()
    private lateinit var toAccountDialog: ToAccountBottomSheetDialog
    private lateinit var fromAccountDialog: FromAccountBottomSheetDialog

    override fun listener() {
        openToAccountDialogListener()
        openFromAccountDialogListener()
    }

    override fun observe() {
        toAccountObserve()
        fromAccountObserve()
    }

    private fun openToAccountDialogListener() {
        with(binding) {
            vToAcc.setOnClickListener {
                toAccountDialog = ToAccountBottomSheetDialog()
                toAccountDialog.setToAccount(this@TransactionFragment)
                toAccountDialog.show(parentFragmentManager, tag)
            }
        }
    }

    private fun openFromAccountDialogListener() {
        with(binding) {
            vFromAcc.setOnClickListener {
                fromAccountDialog = FromAccountBottomSheetDialog()
                fromAccountDialog.setFromAccountListener(this@TransactionFragment)
                fromAccountDialog.show(parentFragmentManager, tag)
            }
        }
    }

    private fun toAccountObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.toAccountStateFlow.collect {
                    with(binding) {
                        if (it != null) {
                            tvToHideNum.visibility = View.VISIBLE
                            tvToIcon.visibility = View.VISIBLE
                            tvToName.visibility = View.VISIBLE
                            tvToAmount.visibility = View.VISIBLE
                            tvToValute.visibility = View.VISIBLE
                            tvToNum.visibility = View.VISIBLE
                            tvToEmpty.visibility = View.GONE

                            tvToName.text = it.accountName
                            tvToValute.text = it.valuteType
                            tvToNum.text = it.accountNumber.takeLast(4)
                        } else {
                            tvToHideNum.visibility = View.GONE
                            tvToIcon.visibility = View.GONE
                            tvToName.visibility = View.GONE
                            tvToAmount.visibility = View.GONE
                            tvToValute.visibility = View.GONE
                            tvToNum.visibility = View.GONE
                            tvToEmpty.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    private fun fromAccountObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.fromAccountStateFlow.collect {
                    with(binding) {
                        if (it != null) {
                            tvFromAmount.visibility = View.VISIBLE
                            tvFromName.visibility = View.VISIBLE
                            tvFromValute.visibility = View.VISIBLE
                            tvFromAccNum.visibility = View.VISIBLE
                            ivFromIcon.visibility = View.VISIBLE
                            tvFromValute.visibility = View.VISIBLE
                            tvFromHideNum.visibility = View.VISIBLE
                            tvFromEmpty.visibility = View.GONE

                            tvFromAmount.text = it.balance.toString()
                            tvFromName.text = it.accountName
                            tvFromValute.text = it.valuteType
                            tvFromAccNum.text = it.accountNumber.takeLast(4)

                        } else {
                            tvFromAmount.visibility = View.GONE
                            tvFromName.visibility = View.GONE
                            tvFromValute.visibility = View.GONE
                            tvFromAccNum.visibility = View.GONE
                            ivFromIcon.visibility = View.GONE
                            tvFromValute.visibility = View.GONE
                            tvFromHideNum.visibility = View.GONE
                            tvFromEmpty.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    override fun getToAccount(toAccountUi: ToAccountUi) {
        viewModel.onEvent(TransactionEvent.UpdateToAccount(toAccountUi = toAccountUi))
    }

    override fun getFromAccount(fromAccountUi: FromAccountUi) {
        viewModel.onEvent(TransactionEvent.UpdateFromAccount(fromAccountUi = fromAccountUi))
    }
}