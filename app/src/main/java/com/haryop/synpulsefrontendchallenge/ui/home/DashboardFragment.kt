package com.haryop.synpulsefrontendchallenge.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.haryop.synpulsefrontendchallenge.databinding.FragmentDashboardBinding
import com.haryop.synpulsefrontendchallenge.utils.BaseFragmentBinding

class DashboardFragment: BaseFragmentBinding<FragmentDashboardBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDashboardBinding
        get() = FragmentDashboardBinding::inflate

    lateinit var viewbinding: FragmentDashboardBinding
    override fun setupView(binding: FragmentDashboardBinding) {
        viewbinding = binding

    }
}