package com.haryop.synpulsefrontendchallenge.ui.titlescreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.haryop.synpulsefrontendchallenge.R
import com.haryop.synpulsefrontendchallenge.databinding.FragmentTitleScreenBinding
import com.haryop.synpulsefrontendchallenge.utils.BaseFragmentBinding

class TitleScreenFragment : BaseFragmentBinding<FragmentTitleScreenBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTitleScreenBinding
        get() = FragmentTitleScreenBinding::inflate

    lateinit var viewbinding: FragmentTitleScreenBinding
    override fun setupView(binding: FragmentTitleScreenBinding) {
        viewbinding = binding
        setupAction(binding.root)
    }

    fun setupAction(view: View) = with(viewbinding) {
        signIn.setOnClickListener {findNavController().navigate(R.id.action_tscreen_to_signin)}
        signUp.setOnClickListener {findNavController().navigate(R.id.action_tscreen_to_signup)}
        about.setOnClickListener {findNavController().navigate(R.id.action_tscreen_to_about)}
    }

}