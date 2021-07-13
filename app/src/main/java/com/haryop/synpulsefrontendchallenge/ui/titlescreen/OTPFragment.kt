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

class OTPFragment : BaseFragmentBinding<FragmentOtpBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentOtpBinding
        get() = FragmentOtpBinding::inflate

    lateinit var viewbinding: FragmentOtpBinding
    override fun setupView(binding: FragmentOtpBinding) {
        viewbinding = binding

        viewbinding.submit.setOnClickListener { onSubmitOTP() }

    }

    fun onSubmitOTP(){
        setlogin()

        var intent = Intent(activity, LandingActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra(ConstantsObj.KEY_TARGET_ACTIVITY, ConstantsObj.VALUE_WELCOMESEARCH_ACTIVITY )
        activity?.startActivity(intent)
    }

    fun setlogin(){
        val sharedPref = activity?.getSharedPreferences(activity?.packageName, Context.MODE_PRIVATE)
        with (sharedPref?.edit()) {
            this?.putString(com.haryop.synpulsefrontendchallenge.utils.ConstantsObj.KEY_ISLOGIN, "KEY_ISLOGIN")
            this?.commit()
        }

        var islogin = sharedPref?.getString(ConstantsObj.KEY_ISLOGIN, "")
        Log.e("splashscreen", "isLogin: " + islogin)
    }
}