package com.haryop.synpulsefrontendchallenge.ui.signinup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.haryop.synpulsefrontendchallenge.R
import com.haryop.synpulsefrontendchallenge.databinding.FragmentSignupBinding
import com.haryop.synpulsefrontendchallenge.utils.BaseFragmentBinding

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
        val navcontroller = findNavController()
        val navgraph = findNavController().graph

        if (navgraph.startDestination == R.id.signinFragment){
            navcontroller.popBackStack()
        }else{
            navcontroller.navigate(R.id.action_signup_to_signin)
        }
    }

    fun onRegister(){
        findNavController().navigate(R.id.action_signup_to_otp)
    }


}