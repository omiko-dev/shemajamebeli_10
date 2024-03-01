package com.example.shemajamebeli___.presentation.screen.transaction.dialog.from_account_bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shemajamebeli___.databinding.FromAccountBottomSheetBinding
import com.example.shemajamebeli___.presentation.adapter.FromAccountCardRecyclerAdapter
import com.example.shemajamebeli___.presentation.model.FromAccountUi
import com.example.shemajamebeli___.presentation.screen.transaction.dialog.from_account_bottom_sheet.event.FromAccountEvent
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class FromAccountBottomSheetDialog: BottomSheetDialogFragment() {
    private val viewModel: FromAccountViewModel by viewModels()
    private var _binding: FromAccountBottomSheetBinding? = null
    private lateinit var adapter: FromAccountCardRecyclerAdapter
    private val binding get() = _binding!!
    private var fromAccountListener: FromAccountListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FromAccountBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindAdapter()
        observeFromAccountList()
        fromAccountCardListener()
    }

    private fun bindAdapter(){
        adapter = FromAccountCardRecyclerAdapter()
        with(binding){
            recycler.adapter = adapter
            recycler.layoutManager = LinearLayoutManager(requireContext())
        }
        viewModel.onEvent(FromAccountEvent.GetFromAccountList)
    }

    private fun observeFromAccountList(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.fromAccountList.collect { state ->
                    state.fromAccount?.let {
                        adapter.submitList(it)
                    }
                }
            }
        }
    }

    private fun fromAccountCardListener(){
        adapter.onClick = {
            fromAccountListener?.getFromAccount(it)
        }
    }

    interface FromAccountListener{
        fun getFromAccount(item: FromAccountUi)
    }

    fun setFromAccountListener(listener: FromAccountListener){
        fromAccountListener = listener
    }
}