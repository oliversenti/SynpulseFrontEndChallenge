package com.haryop.synpulsefrontendchallenge.ui.titlescreen

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.haryop.synpulsefrontendchallenge.ui.LandingActivity
import com.haryop.synpulsefrontendchallenge.databinding.FragmentOtpBinding
import com.haryop.synpulsefrontendchallenge.utils.BaseFragmentBinding
import com.haryop.synpulsefrontendchallenge.utils.ConstantsObj

class OTPFragment : BaseFragmentBinding<FragmentOtpBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentOtpBinding
        get() = FragmentOtpBinding::inflate

    lateinit var viewbinding: FragmentOtpBinding
    override fun setupView(binding: FragmentOtpBinding) {
        viewbinding = binding

        viewbinding.submit.setOnClickListener { onSubmitOTP() }

    }

    fun onSubmitOTP(){
        var intent = Intent(activity, LandingActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra(ConstantsObj.KEY_TARGET_ACTIVITY, ConstantsObj.VALUE_WELCOMESEARCH_ACTIVITY )
        activity?.startActivity(intent)
    }
}