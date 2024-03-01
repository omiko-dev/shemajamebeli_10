package com.example.shemajamebeli___.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shemajamebeli___.databinding.FromAccountCardBinding
import com.example.shemajamebeli___.presentation.model.FromAccountUi

class FromAccountCardRecyclerAdapter :
    ListAdapter<FromAccountUi, FromAccountCardRecyclerAdapter.FromAccountCardViewHolder>(
        diffUtil
    ) {

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<FromAccountUi>() {
            override fun areItemsTheSame(oldItem: FromAccountUi, newItem: FromAccountUi): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FromAccountUi,
                newItem: FromAccountUi,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    lateinit var onClick: (FromAccountUi) -> Unit

    inner class FromAccountCardViewHolder(private val binding: FromAccountCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() = with(binding) {
            val item = currentList[adapterPosition]
            tvAccName.text = item.accountName
            tvAmount.text = item.balance.toString()
            tvType.text = item.cardType
        }

        fun listener(){
            binding.vFromAcc.setOnClickListener {
                val item = currentList[adapterPosition]
                onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FromAccountCardViewHolder {
        return FromAccountCardViewHolder(
            FromAccountCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FromAccountCardViewHolder, position: Int) {
        with(holder){
            bind()
            listener()
        }
    }
}