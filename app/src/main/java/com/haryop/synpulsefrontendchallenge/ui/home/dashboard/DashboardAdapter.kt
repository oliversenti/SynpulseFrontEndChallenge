package com.haryop.synpulsefrontendchallenge.ui.home.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haryop.synpulsefrontendchallenge.data.CompanyListItemData
import com.haryop.synpulsefrontendchallenge.data.DashboardTopItem
import com.haryop.synpulsefrontendchallenge.databinding.DashboardGainerlosersItemLayoutBinding
import com.haryop.synpulsefrontendchallenge.databinding.DashboardItemLayoutBinding
import com.haryop.synpulsefrontendchallenge.databinding.DashboardTitleItemLayoutBinding

class DashboardAdapter(private val listener: DashboardListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface DashboardListener {
        fun onClickedDetailCompany()
    }

    private val items = ArrayList<Any>()

    fun setItems(items: ArrayList<Any>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    val ITEM_TYPE_DASHBOARD_GAINER_LOSSER_LAYOUT = 0
    val ITEM_TYPE_DASHBOARD_ITEM_LAYOUT = 1
    val ITEM_TYPE_DASHBOARD_TITLE_LAYOUT = 2

    override fun getItemViewType(position: Int): Int {
        if (items[position] is DashboardTopItem) {
            return ITEM_TYPE_DASHBOARD_GAINER_LOSSER_LAYOUT
        } else if (items[position] is String) {
            return ITEM_TYPE_DASHBOARD_TITLE_LAYOUT
        } else {
            return ITEM_TYPE_DASHBOARD_ITEM_LAYOUT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            ITEM_TYPE_DASHBOARD_GAINER_LOSSER_LAYOUT -> {
                val binding: DashboardGainerlosersItemLayoutBinding =
                    DashboardGainerlosersItemLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return DashboardGainerLosserViewHolder(binding, listener)
            }
            ITEM_TYPE_DASHBOARD_TITLE_LAYOUT -> {
                val binding: DashboardTitleItemLayoutBinding =
                    DashboardTitleItemLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return DashboardTitleViewHolder(binding, listener)
            }
            else -> {
                val binding: DashboardItemLayoutBinding =
                    DashboardItemLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return DashboardViewHolder(binding, listener)
            }

        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.getItemViewType()) {
            ITEM_TYPE_DASHBOARD_GAINER_LOSSER_LAYOUT -> {
                var mHolder = holder as DashboardGainerLosserViewHolder
                mHolder.bind(items[position] as DashboardTopItem)
            }
            ITEM_TYPE_DASHBOARD_ITEM_LAYOUT -> {
                var mHolder = holder as DashboardViewHolder
                mHolder.bind(items[position] as CompanyListItemData)
            }
            ITEM_TYPE_DASHBOARD_TITLE_LAYOUT -> {
            }
        }
    }

}