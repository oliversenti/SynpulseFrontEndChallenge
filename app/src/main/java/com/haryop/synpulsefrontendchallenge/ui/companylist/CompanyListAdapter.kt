package com.haryop.synpulsefrontendchallenge.ui.companylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haryop.synpulsefrontendchallenge.data.entities.SearchEndpointEntity
import com.haryop.synpulsefrontendchallenge.databinding.ItemBottomSpaceBinding
import com.haryop.synpulsefrontendchallenge.databinding.ItemCompanyListBinding
import com.haryop.synpulsefrontendchallenge.ui.BottomspaceViewHolder

class CompanyListAdapter(private val listener: CompanyItemListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface CompanyItemListener {
        fun onClickedDetailCompany(symbol: String, name:String)
        fun onClickedFollowCompany()
    }

    private val items = ArrayList<Any>()

    fun setItems(items: ArrayList<Any>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    val ITEM_TYPE_SEARCHITEM_LAYOUT = 0
    val ITEM_TYPE_BOTTOMSPACE_LAYOUT = 1
    override fun getItemViewType(position: Int): Int {
        if (items[position] is SearchEndpointEntity) {
            return ITEM_TYPE_SEARCHITEM_LAYOUT
        } else {
            return ITEM_TYPE_BOTTOMSPACE_LAYOUT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            ITEM_TYPE_SEARCHITEM_LAYOUT -> {
                val binding: ItemCompanyListBinding =
                    ItemCompanyListBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return CompanyViewHolder(binding, listener)
            }

            else -> {
                val binding: ItemBottomSpaceBinding =
                    ItemBottomSpaceBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return BottomspaceViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.getItemViewType()) {
            ITEM_TYPE_SEARCHITEM_LAYOUT -> {
                var mHolder = holder as CompanyViewHolder
                mHolder.bind(items[position] as SearchEndpointEntity)
            }

            ITEM_TYPE_BOTTOMSPACE_LAYOUT -> {
            }

        }
    }

}