package com.example.cekresi.view.activity

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.cekresi.R
import com.example.cekresi.databinding.ItemHistoryBinding
import com.example.cekresi.helper.convertDate
import com.example.cekresi.network.response.HistoryItem
import com.example.cekresi.view.activity.MainAdapter.ViewHolder

class MainAdapter
    : BaseQuickAdapter<HistoryItem, ViewHolder>(
    R.layout.item_history
) {

    override fun convert(holder: ViewHolder, item: HistoryItem) {
        holder.bindData(item)
    }

    class ViewHolder(view: View) : BaseViewHolder(view) {
        private val binding = ItemHistoryBinding.bind(view)

        fun bindData(item: HistoryItem) {
            with(binding) {
                txtDate.text = convertDate(item.date, "dd/MMMM/yyyy " + "," + " HH:mm")
                txtDesc.text = item.desc
            }
        }

    }
}