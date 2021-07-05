package com.haryop.synpulsefrontendchallenge.ui.home.dashboard

import androidx.recyclerview.widget.RecyclerView
import com.haryop.synpulsefrontendchallenge.data.CompanyListItemData
import com.haryop.synpulsefrontendchallenge.databinding.DashboardItemLayoutBinding

class DashboardViewHolder(
    private val itemBinding: DashboardItemLayoutBinding,
    private val listener: DashboardAdapter.DashboardListener
) :
    RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var company: CompanyListItemData

    fun bind(item: CompanyListItemData) = with(itemBinding) {
        company = item
        symbol.text = item.symbol
        name.text = item.name
    }

}