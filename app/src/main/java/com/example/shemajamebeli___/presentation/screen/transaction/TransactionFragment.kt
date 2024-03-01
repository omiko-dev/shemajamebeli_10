package com.example.shemajamebeli___.presentation.screen.transaction


import android.util.Log
import com.example.shemajamebeli___.databinding.FragmentTransactionBinding
import com.example.shemajamebeli___.presentation.base.BaseFragment
import com.example.shemajamebeli___.presentation.model.FromAccountUi
import com.example.shemajamebeli___.presentation.model.ToAccountUi
import com.example.shemajamebeli___.presentation.screen.transaction.dialog.from_account_bottom_sheet.FromAccountBottomSheetDialog
import com.example.shemajamebeli___.presentation.screen.transaction.dialog.to_account_bottom_sheet.ToAccountBottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionFragment :
    BaseFragment<FragmentTransactionBinding>(FragmentTransactionBinding::inflate),
    ToAccountBottomSheetDialog.ToAccountListener, FromAccountBottomSheetDialog.FromAccountListener {
    private lateinit var toAccountDialog: ToAccountBottomSheetDialog
    private lateinit var fromAccountDialog: FromAccountBottomSheetDialog

    override fun listener() {
        openToAccountDialogListener()
        openFromAccountDialogListener()
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
                fromAccountDialog.show(parentFragmentManager, tag)
            }
        }
    }

    override fun getToAccount(toAccountUi: ToAccountUi) {

    }

    override fun getFromAccount(item: FromAccountUi) {
        Log.i("omiko", item.toString())
    }
}