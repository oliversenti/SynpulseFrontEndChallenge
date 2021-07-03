package com.haryop.synpulsefrontendchallenge.ui.signinup

import android.view.LayoutInflater
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.haryop.synpulsefrontendchallenge.R
import com.haryop.synpulsefrontendchallenge.databinding.ActivitySigninupBinding
import com.haryop.synpulsefrontendchallenge.utils.BaseFragmentActivityBinding
import com.haryop.synpulsefrontendchallenge.utils.ConstantsObj

class SignInUpActivity : BaseFragmentActivityBinding<ActivitySigninupBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivitySigninupBinding
        get() = ActivitySigninupBinding::inflate

    override fun setupView(binding: ActivitySigninupBinding) {
        setUpAction(binding.root)
    }

    fun setUpAction(view: View) = with(binding) {
        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_signinup_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        val newnavgraph = navController.graph
        var sign = intent.getIntExtra("sign", 0)
        if (sign == ConstantsObj.TO_SIGN_UP) {
            newnavgraph.startDestination = R.id.signupFragment
        }
        navController.graph = newnavgraph

        val appBarConfiguration: AppBarConfiguration = AppBarConfiguration(navController.graph)
        signinupToolbar.setupWithNavController(navController, appBarConfiguration)
    }

}