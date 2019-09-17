package com.hyunyong.test.lotto.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hyunyong.test.lotto.R
import com.hyunyong.test.lotto.data.Lotto
import com.hyunyong.test.lotto.databinding.ItemLottoBinding

class LookupRecyclerAdapter :
    RecyclerView.Adapter<LookupRecyclerAdapter.LookupRecyclerViewHolder>() {

    private val lottoList = ArrayList<Lotto>()

    fun addLottoList(items: List<Lotto>) {
        lottoList.clear()
        lottoList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LookupRecyclerViewHolder =
        LookupRecyclerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_lotto, parent, false)
        )

    override fun getItemCount(): Int =
        lottoList.size

    override fun onBindViewHolder(holder: LookupRecyclerViewHolder, position: Int) {
        holder.bindData(lottoList[position])
    }

    inner class LookupRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(lotto: Lotto) {
            val binding = DataBindingUtil.bind<ItemLottoBinding>(itemView)
            binding?.lotto = lotto
        }
    }
}