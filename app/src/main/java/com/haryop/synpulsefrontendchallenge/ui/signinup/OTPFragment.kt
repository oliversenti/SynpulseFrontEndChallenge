package com.haryop.synpulsefrontendchallenge.ui.signinup

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.haryop.synpulsefrontendchallenge.ui.home.MainActivity
import com.haryop.synpulsefrontendchallenge.databinding.FragmentOtpBinding
import com.haryop.synpulsefrontendchallenge.utils.BaseFragmentBinding

class OTPFragment : BaseFragmentBinding<FragmentOtpBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentOtpBinding
        get() = FragmentOtpBinding::inflate

    lateinit var viewbinding: FragmentOtpBinding
    override fun setupView(binding: FragmentOtpBinding) {
        viewbinding = binding

        viewbinding.submit.setOnClickListener { onSubmitOTP() }

    }

    fun onSubmitOTP(){
        var intent = Intent(activity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        activity?.startActivity(intent)
    }
}