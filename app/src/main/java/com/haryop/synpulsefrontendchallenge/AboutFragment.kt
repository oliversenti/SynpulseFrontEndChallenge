package com.haryop.synpulsefrontendchallenge

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.haryop.synpulsefrontendchallenge.databinding.FragmentAboutBinding
import com.haryop.synpulsefrontendchallenge.utils.BaseFragmentBinding


class AboutFragment: BaseFragmentBinding<FragmentAboutBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAboutBinding
        get() = FragmentAboutBinding::inflate

    lateinit var viewbinding:FragmentAboutBinding
    override fun setupView(binding: FragmentAboutBinding) {
        viewbinding = binding
        setUpAction(binding.root)
    }

    fun setUpAction(view: View) = with(viewbinding){
        val filename = "about_content.html"
        webviewAbout.getSettings().setJavaScriptEnabled(true);
        webviewAbout.loadUrl("file:///android_asset/" + filename);
    }

}