package com.haryop.synpulsefrontendchallenge

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import com.haryop.synpulsefrontendchallenge.databinding.ActivityLandingBinding
import com.haryop.synpulsefrontendchallenge.ui.home.MainActivity
import com.haryop.synpulsefrontendchallenge.ui.signinup.SignInUpActivity
import com.haryop.synpulsefrontendchallenge.utils.BaseActivityBinding
import com.haryop.synpulsefrontendchallenge.utils.ConstantsObj

class LandingActivity : BaseActivityBinding<ActivityLandingBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityLandingBinding
        get() = ActivityLandingBinding::inflate

    override fun setupView(binding: ActivityLandingBinding) {
        setupAction(binding.root)
    }

    fun setupAction(view: View) = with(binding) {
        signIn.setOnClickListener { openSignInUpActivity(ConstantsObj.TO_SIGN_IN) }
        signUp.setOnClickListener { openSignInUpActivity(ConstantsObj.TO_SIGN_UP) }
        about.setOnClickListener { openAboutActivity() }
    }

    fun openMainActivity() {
        var intent = Intent(this@LandingActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun openAboutActivity() {
        var intent = Intent(this@LandingActivity, AboutActivity::class.java)
        startActivity(intent)
    }

    fun openSignInUpActivity(sign:Int) {
        var intent = Intent(this@LandingActivity, SignInUpActivity::class.java)
        intent.putExtra("sign", sign)
        startActivity(intent)
    }


}