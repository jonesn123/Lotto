package com.hyunyong.test.lotto.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hyunyong.test.lotto.R
import com.hyunyong.test.lotto.data.OrderNumber
import com.hyunyong.test.lotto.databinding.ItemOrderingBinding

class FrequencyRecyclerAdapter :
    RecyclerView.Adapter<FrequencyRecyclerAdapter.FrequencyRecyclerViewHolder>() {

    private val numbers = ArrayList<OrderNumber>()

    fun addNumbers(items: List<OrderNumber>) {
        numbers.clear()
        numbers.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrequencyRecyclerViewHolder =
        FrequencyRecyclerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_ordering, parent, false)
        )

    override fun getItemCount(): Int =
        numbers.size

    override fun onBindViewHolder(holder: FrequencyRecyclerViewHolder, position: Int) {
        holder.bindData(numbers[position])
    }

    inner class FrequencyRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(number: OrderNumber) {
            val binding = DataBindingUtil.bind<ItemOrderingBinding>(itemView)
            binding?.number = number
        }
    }
}