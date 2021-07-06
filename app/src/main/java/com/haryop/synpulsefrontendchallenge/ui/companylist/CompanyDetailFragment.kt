package com.haryop.synpulsefrontendchallenge.ui.companylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.haryop.synpulsefrontendchallenge.R
import com.haryop.synpulsefrontendchallenge.data.entities.GlobalQuoeEndpointEntity
import com.haryop.synpulsefrontendchallenge.data.entities.QuoteEndpointEntity
import com.haryop.synpulsefrontendchallenge.databinding.FragmentCompanyDetailBinding
import com.haryop.synpulsefrontendchallenge.utils.BaseFragmentBinding
import com.haryop.synpulsefrontendchallenge.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CompanyDetailFragment : BaseFragmentBinding<FragmentCompanyDetailBinding>() {


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCompanyDetailBinding
        get() = FragmentCompanyDetailBinding::inflate

    lateinit var viewbinding: FragmentCompanyDetailBinding
    override fun setupView(binding: FragmentCompanyDetailBinding) {
        viewbinding = binding
        setupAction(viewbinding.root)

        arguments?.getString("symbol")?.let {
            viewModel.start(it)
        }
        setupObservers()

//        try {
//            arguments?.getString("symbol")?.let { viewModel.start(it) }
//            setupObservers()
//        }catch (e:Exception){
//            Toast.makeText(requireContext(), "Request Timeout", Toast.LENGTH_SHORT).show()
//        }

    }

    fun setupAction(view: View) = with(viewbinding) {

    }

    private val viewModel: CompanyDetailViewModel by viewModels()
    private fun setupObservers() {
        viewModel.getQuoteEndpoint.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    viewbinding.progressBar.visibility = View.GONE
                    if (it.data != null && it.data.global_quote!=null) {
                        var item = it.data!!.global_quote
                        setUpPage(item)

                    } else {
                        Timber.e("getQuoteEndpoint.observe: SUCCESS tapi null")
                        Toast.makeText(requireContext(), it.message?:"Request Timeout", Toast.LENGTH_SHORT).show()
                    }
                }
                Resource.Status.ERROR -> {
                    viewbinding.progressBar.visibility = View.GONE
                    Timber.e("getQuoteEndpoint.observe: error")
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                Resource.Status.LOADING -> {
                    Timber.e("getQuoteEndpoint.observe: LOADING")
                    viewbinding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    fun setUpPage(item: GlobalQuoeEndpointEntity) = with(viewbinding) {
        symbol.text = arguments?.getString("symbol")
        name.text = arguments?.getString("name")
        changePercent.text = item.change_percent

        if (item.change_percent?.substring(0, 1).equals("-")){
            changePercent.setTextColor(resources.getColor(R.color.red))
        }

    }
}