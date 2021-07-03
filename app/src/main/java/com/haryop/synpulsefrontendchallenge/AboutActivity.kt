package com.haryop.synpulsefrontendchallenge

import android.view.LayoutInflater
import android.view.View
import com.haryop.synpulsefrontendchallenge.databinding.ActivityAboutBinding
import com.haryop.synpulsefrontendchallenge.utils.BaseActivityBinding

class AboutActivity: BaseActivityBinding<ActivityAboutBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityAboutBinding
        get() = ActivityAboutBinding::inflate

    override fun setupView(binding: ActivityAboutBinding) {
        setUpAction(binding.root)
    }

    fun setUpAction(view: View) = with(binding){
        val filename = "about_content.html"
        webviewAbout.getSettings().setJavaScriptEnabled(true);
        webviewAbout.loadUrl("file:///android_asset/" + filename);
    }
}