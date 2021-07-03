package com.haryop.synpulsefrontendchallenge

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import com.haryop.synpulsefrontendchallenge.databinding.ActivityLandingBinding
import com.haryop.synpulsefrontendchallenge.utils.BaseActivityBinding

class LandingActivity : BaseActivityBinding<ActivityLandingBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityLandingBinding
        get() = ActivityLandingBinding::inflate

    override fun setupView(binding: ActivityLandingBinding) {
        setupAction(binding.root)
    }

    fun setupAction(view: View) = with(binding) {
        signIn.setOnClickListener { openMainActivity() }
        signUp.setOnClickListener { openMainActivity() }
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


}