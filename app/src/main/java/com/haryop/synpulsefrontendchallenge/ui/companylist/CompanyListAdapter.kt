package com.haryop.synpulsefrontendchallenge.ui.companylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haryop.synpulsefrontendchallenge.data.CompanyListItemData
import com.haryop.synpulsefrontendchallenge.databinding.ItemCompanyListBinding

class CompanyListAdapter(private val listener: CompanyItemListener) :
    RecyclerView.Adapter<CompanyViewHolder>() {

    interface CompanyItemListener {
        fun onClickedDetailCompany()
        fun onClickedFollowCompany()
    }

    private val items = ArrayList<CompanyListItemData>()

    fun setItems(items: ArrayList<CompanyListItemData>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val binding: ItemCompanyListBinding =
            ItemCompanyListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CompanyViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) =
        holder.bind(items[position])
}