package com.haryop.synpulsefrontendchallenge.ui.home.dashboard

import androidx.recyclerview.widget.RecyclerView
import com.haryop.synpulsefrontendchallenge.data.CompanyListItemData
import com.haryop.synpulsefrontendchallenge.data.DashboardTopItem
import com.haryop.synpulsefrontendchallenge.databinding.DashboardGainerlosersItemLayoutBinding

class DashboardGainerLosserViewHolder(
    private val itemBinding: DashboardGainerlosersItemLayoutBinding,
    private val listener: DashboardAdapter.DashboardListener
) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(item: DashboardTopItem) = with(itemBinding) {
        title.text = item.title
    }

}