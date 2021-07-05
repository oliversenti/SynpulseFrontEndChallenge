package com.haryop.synpulsefrontendchallenge.ui.home.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.haryop.synpulsefrontendchallenge.data.CompanyListItemData
import com.haryop.synpulsefrontendchallenge.data.DashboardTopItem
import com.haryop.synpulsefrontendchallenge.databinding.FragmentDashboardBinding
import com.haryop.synpulsefrontendchallenge.utils.BaseFragmentBinding

class DashboardFragment : BaseFragmentBinding<FragmentDashboardBinding>(),
    DashboardAdapter.DashboardListener {

    lateinit var adapter:DashboardAdapter
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDashboardBinding
        get() = FragmentDashboardBinding::inflate

    lateinit var viewbinding: FragmentDashboardBinding
    override fun setupView(binding: FragmentDashboardBinding) {
        viewbinding = binding

        setupRecyclerView()
    }

    private fun setupRecyclerView() = with(viewbinding) {
        adapter = DashboardAdapter(this@DashboardFragment)

        var items = ArrayList<Any>()
        var dashboardTopSubItems = ArrayList<CompanyListItemData>()
        dashboardTopSubItems.add(CompanyListItemData("AMZN", "Amazon.com Inc", "+15"))
        dashboardTopSubItems.add(CompanyListItemData("AAPL", "Apple Inc", "+15"))
        dashboardTopSubItems.add(CompanyListItemData("XOM", "Exxon Mobil Corp", "+15"))
        dashboardTopSubItems.add(CompanyListItemData("MSFT", "Microsoft Company", "+15"))
        dashboardTopSubItems.add(CompanyListItemData("IBM", "International Business Machines Corporation", "-15"))

        var dashboardTopItem = DashboardTopItem("Gainer and Losser", dashboardTopSubItems)
        items.add(dashboardTopItem)

        items.add("Your WatchLlst")

        for(i in 0..9){
            items.add(CompanyListItemData("MSFT", "Microsoft Company", "+15"))
            items.add(CompanyListItemData("IBM", "International Business Machines Corporation", "-15"))
            items.add(CompanyListItemData("AMZN", "Amazon.com Inc", "+15"))
        }

        adapter.setItems(items)

        recyclerviewDashboardHome.layoutManager = LinearLayoutManager(requireContext())
        recyclerviewDashboardHome.adapter = adapter

    }

    override fun onClickedDetailCompany() {

    }

}