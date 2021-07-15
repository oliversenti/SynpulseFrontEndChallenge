package com.haryop.synpulsefrontendchallenge.ui.titlescreen

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.haryop.synpulsefrontendchallenge.ui.LandingActivity
import com.haryop.synpulsefrontendchallenge.databinding.FragmentOtpBinding
import com.haryop.synpulsefrontendchallenge.utils.BaseFragmentBinding
import com.haryop.synpulsefrontendchallenge.utils.ConstantsObj
import com.haryop.synpulsefrontendchallenge.utils.showToast

class OTPFragment : BaseFragmentBinding<FragmentOtpBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentOtpBinding
        get() = FragmentOtpBinding::inflate

    lateinit var viewbinding: FragmentOtpBinding
    override fun setupView(binding: FragmentOtpBinding) {
        viewbinding = binding

        viewbinding.submit.setOnClickListener { onSubmitOTP() }

    }

    fun onSubmitOTP()=with(viewbinding){
        val otp_code = otpField.text.toString()
        if (otp_code.isNullOrEmpty() || otp_code.equals("")){
            showToast("Phone number is empty" )
            return
        }

        (activity as LandingActivity).onVerifyPhoneNumberWithCode(otp_code)
    }

}