package com.haryop.synpulsefrontendchallenge.ui.companylist

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.haryop.synpulsefrontendchallenge.R
import com.haryop.synpulsefrontendchallenge.data.CompanyListItemData
import com.haryop.synpulsefrontendchallenge.databinding.FragmentBrowseCompanyBinding
import com.haryop.synpulsefrontendchallenge.ui.home.MainActivity
import com.haryop.synpulsefrontendchallenge.utils.BaseFragmentBinding

class BrowseCompanyFragment : BaseFragmentBinding<FragmentBrowseCompanyBinding>(),
    CompanyListAdapter.CompanyItemListener {

    private lateinit var adapter: CompanyListAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentBrowseCompanyBinding
        get() = FragmentBrowseCompanyBinding::inflate

    lateinit var viewbinding: FragmentBrowseCompanyBinding
    override fun setupView(binding: FragmentBrowseCompanyBinding) {
        viewbinding = binding
        setUpAction(binding.root)
    }

    fun setUpAction(view: View) = with(viewbinding) {
        btnSubmit.setOnClickListener { openMainActivity() }
        btnSubmit.visibility = View.VISIBLE

        setupRecyclerView()
    }

    fun openMainActivity() {
        var intent = Intent(activity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        activity?.startActivity(intent)
    }

    private fun setupRecyclerView() = with(viewbinding) {
        adapter = CompanyListAdapter(this@BrowseCompanyFragment)

        var items = ArrayList<CompanyListItemData>()
        for(i in 0..9){
            items.add(CompanyListItemData("MSFT", "Microsoft Company"))
            items.add(CompanyListItemData("IBM", "International Business Machines Corporation"))
            items.add(CompanyListItemData("AMZN", "Amazon.com Inc"))
        }

        adapter.setItems(items)

        recyclerviewCompanyList.layoutManager = LinearLayoutManager(requireContext())
        recyclerviewCompanyList.adapter = adapter

    }

    override fun onClickedDetailCompany() {
        findNavController().navigate(R.id.action_itemcompany_to_detail)
    }

    override fun onClickedFollowCompany() {

    }


}