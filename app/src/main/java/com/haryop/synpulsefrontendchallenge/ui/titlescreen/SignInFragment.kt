package com.haryop.synpulsefrontendchallenge.ui.titlescreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.haryop.synpulsefrontendchallenge.R
import com.haryop.synpulsefrontendchallenge.databinding.FragmentSigninBinding
import com.haryop.synpulsefrontendchallenge.ui.LandingActivity
import com.haryop.synpulsefrontendchallenge.utils.BaseFragmentBinding
import com.haryop.synpulsefrontendchallenge.utils.showToast

class SignInFragment : BaseFragmentBinding<FragmentSigninBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSigninBinding
        get() = FragmentSigninBinding::inflate

    lateinit var viewbinding: FragmentSigninBinding
    override fun setupView(binding: FragmentSigninBinding) {
        viewbinding = binding
        viewbinding.signUp.setOnClickListener { onSignUp() }
        viewbinding.enter.setOnClickListener { onEnter() }
    }

    fun onSignUp(){
        if (findNavController().previousBackStackEntry?.destination?.id == R.id.signupFragment){
            findNavController().popBackStack()
        }else{
            findNavController().navigate(R.id.action_signin_to_signup)
        }
    }

    fun onEnter()=with(viewbinding){
        val phonenumber = phoneNumberField.text.toString()
        if (phonenumber.isNullOrEmpty() || phonenumber.equals("")){
            showToast("Phone number is empty" )
            return
        }
        (activity as LandingActivity).onSendVerificationCode(phonenumber, R.id.action_signin_to_otp)
    }

}