package com.haryop.synpulsefrontendchallenge.ui.companylist

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.haryop.synpulsefrontendchallenge.R
import com.haryop.synpulsefrontendchallenge.data.CompanyListItemData
import com.haryop.synpulsefrontendchallenge.data.entities.SearchEndpointEntity
import com.haryop.synpulsefrontendchallenge.databinding.ItemCompanyListBinding

class CompanyViewHolder(
    private val itemBinding: ItemCompanyListBinding,
    private val listener: CompanyListAdapter.CompanyItemListener
) :
    RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var company: SearchEndpointEntity

    fun bind(item: SearchEndpointEntity) = with(itemBinding) {
        company = item
        symbol.text = item.symbol
        name.text = item.name

        btnDetail.setOnClickListener { listener.onClickedDetailCompany(item.symbol, item.name) }
        btnFollow.setOnClickListener {
            if(btnFollow.text.equals(btnFollow.context.resources.getString(R.string.follow))){
                btnFollow.text = btnFollow.context.resources.getString(R.string.followed)
                btnFollow.setBackgroundColor(btnFollow.context.resources.getColor(R.color.light_grey))
                listener.onClickedFollowCompany()
            }else{
                btnFollow.text = btnFollow.context.resources.getString(R.string.follow)
                btnFollow.setBackgroundColor(btnFollow.context.resources.getColor(R.color.grey))
                listener.onClickedFollowCompany()
            }
        }
    }


}