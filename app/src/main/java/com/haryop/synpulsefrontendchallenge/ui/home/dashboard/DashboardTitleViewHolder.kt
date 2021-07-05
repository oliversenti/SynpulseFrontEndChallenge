package com.haryop.synpulsefrontendchallenge.ui.home.dashboard

import androidx.recyclerview.widget.RecyclerView
import com.haryop.synpulsefrontendchallenge.databinding.DashboardTitleItemLayoutBinding

class DashboardTitleViewHolder(
    private val itemBinding: DashboardTitleItemLayoutBinding,
    private val listener: DashboardAdapter.DashboardListener
) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(item: String) = with(itemBinding) {
        title.text = item
    }
}
