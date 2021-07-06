package com.haryop.synpulsefrontendchallenge.ui.companylist

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.haryop.synpulsefrontendchallenge.R
import com.haryop.synpulsefrontendchallenge.databinding.FragmentBrowseCompanyBinding
import com.haryop.synpulsefrontendchallenge.ui.home.MainActivity
import com.haryop.synpulsefrontendchallenge.utils.BaseFragmentBinding
import com.haryop.synpulsefrontendchallenge.utils.Resource
import com.haryop.synpulsefrontendchallenge.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
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

        viewbinding.searchField.text.clear()
        viewModel.start("BA")
        setupObservers()

        onSearch()
    }

    fun onSearch() = with(viewbinding) {
        searchField.setOnEditorActionListener() { v, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    viewModel.start(searchField.text.toString())
                    setupObservers()
                    hideKeyboard()
                    true
                }
                else -> false
            }
        }
    }

    fun openMainActivity() {
        var intent = Intent(activity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        activity?.startActivity(intent)
    }

    private fun setupRecyclerView() = with(viewbinding) {
        adapter = CompanyListAdapter(this@BrowseCompanyFragment)
        recyclerviewCompanyList.layoutManager = LinearLayoutManager(requireContext())
        recyclerviewCompanyList.adapter = adapter
    }

    private val viewModel: SearchEndpointViewModel by viewModels()
    private fun setupObservers() {
        viewModel.getSearchEndpoint.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    viewbinding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) {
                        var items = ArrayList<Any>()
                        items.addAll(ArrayList(it.data))
                        items.add(adapter.ITEM_TYPE_BOTTOMSPACE_LAYOUT)
                        adapter.setItems(items)


                    } else {
                        Timber.e("getSearchEndpoint.observe: SUCCESS tapi null")
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                }
                Resource.Status.ERROR -> {
                    viewbinding.progressBar.visibility = View.GONE
                    Timber.e("getSearchEndpoint.observe: error")
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                Resource.Status.LOADING -> {
                    Timber.e("getSearchEndpoint.observe: LOADING")
                    viewbinding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onClickedDetailCompany(symbol: String, name: String) {
        findNavController().navigate(
            R.id.action_itemcompany_to_detail,
            bundleOf("symbol" to symbol, "name" to name)
        )
    }

    override fun onClickedFollowCompany() {

    }


}