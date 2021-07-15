package com.haryop.synpulsefrontendchallenge.ui.titlescreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.haryop.synpulsefrontendchallenge.R
import com.haryop.synpulsefrontendchallenge.databinding.FragmentSignupBinding
import com.haryop.synpulsefrontendchallenge.ui.LandingActivity
import com.haryop.synpulsefrontendchallenge.utils.BaseFragmentBinding
import com.haryop.synpulsefrontendchallenge.utils.showToast


class SignUpFragment : BaseFragmentBinding<FragmentSignupBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSignupBinding
        get() = FragmentSignupBinding::inflate

    lateinit var viewbinding: FragmentSignupBinding
    override fun setupView(binding: FragmentSignupBinding) {
        viewbinding = binding
        viewbinding.signIn.setOnClickListener { onSignIn() }
        viewbinding.register.setOnClickListener { onRegister() }
    }

    fun onSignIn() {
        if (findNavController().previousBackStackEntry?.destination?.id == R.id.signinFragment){
            findNavController().popBackStack()
        }else{
            findNavController().navigate(R.id.action_signup_to_signin)
        }
    }

    fun onRegister()=with(viewbinding){
        val phonenumber = phoneNumberField.text.toString()
        if (phonenumber.isNullOrEmpty() || phonenumber.equals("")){
            showToast("Phone number is empty" )
            return
        }
        (activity as LandingActivity).onSendVerificationCode(phonenumber, R.id.action_signup_to_otp)
    }

}