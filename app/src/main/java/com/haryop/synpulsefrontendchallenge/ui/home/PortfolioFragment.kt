package com.haryop.synpulsefrontendchallenge.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.haryop.synpulsefrontendchallenge.databinding.FragmentPortfolioBinding
import com.haryop.synpulsefrontendchallenge.utils.BaseFragmentBinding

class PortfolioFragment: BaseFragmentBinding<FragmentPortfolioBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPortfolioBinding
        get() = FragmentPortfolioBinding::inflate

    lateinit var viewbinding: FragmentPortfolioBinding
    override fun setupView(binding: FragmentPortfolioBinding) {
        viewbinding = binding

    }
}