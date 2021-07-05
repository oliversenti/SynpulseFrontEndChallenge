package com.haryop.synpulsefrontendchallenge.ui.companylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.haryop.synpulsefrontendchallenge.databinding.FragmentCompanyDetailBinding
import com.haryop.synpulsefrontendchallenge.utils.BaseFragmentBinding

class CompanyDetailFragment: BaseFragmentBinding<FragmentCompanyDetailBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCompanyDetailBinding
        get() = FragmentCompanyDetailBinding::inflate

    lateinit var viewbinding: FragmentCompanyDetailBinding
    override fun setupView(binding: FragmentCompanyDetailBinding) {
        viewbinding = binding
        setupAction(viewbinding.root)
    }

    fun setupAction(view: View)=with(viewbinding){

    }
}