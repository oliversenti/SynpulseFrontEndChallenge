package com.haryop.synpulsefrontendchallenge.ui

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.haryop.synpulsefrontendchallenge.R
import com.haryop.synpulsefrontendchallenge.data.CompanyListItemData
import com.haryop.synpulsefrontendchallenge.data.entities.SearchEndpointEntity
import com.haryop.synpulsefrontendchallenge.databinding.ItemBottomSpaceBinding
import com.haryop.synpulsefrontendchallenge.databinding.ItemCompanyListBinding

class BottomspaceViewHolder(
    private val itemBinding: ItemBottomSpaceBinding
) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind() = with(itemBinding) {
    }
}
