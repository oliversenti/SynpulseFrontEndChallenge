package com.haryop.synpulsefrontendchallenge.ui.signinup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.haryop.synpulsefrontendchallenge.R
import com.haryop.synpulsefrontendchallenge.databinding.FragmentSigninBinding
import com.haryop.synpulsefrontendchallenge.utils.BaseFragmentBinding

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
        val navcontroller = findNavController()
        val navgraph = findNavController().graph

        if (navgraph.startDestination == R.id.signupFragment){
            navcontroller.popBackStack()
        }else{
            navcontroller.navigate(R.id.action_signin_to_signup)
        }
    }

    fun onEnter(){
        findNavController().navigate(R.id.action_signin_to_otp)
    }

}