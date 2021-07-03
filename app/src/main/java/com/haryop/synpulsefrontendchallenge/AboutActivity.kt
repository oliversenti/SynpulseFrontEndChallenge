package com.haryop.synpulsefrontendchallenge

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import com.haryop.synpulsefrontendchallenge.databinding.ActivityAboutBinding
import com.haryop.synpulsefrontendchallenge.utils.BaseActivityBinding

class AboutActivity: BaseActivityBinding<ActivityAboutBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityAboutBinding
        get() = ActivityAboutBinding::inflate

    override fun setupView(binding: ActivityAboutBinding) {
        setUpAction(binding.root)
        var actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.title = resources.getString(R.string.about_title_toolbar)
    }

    fun setUpAction(view: View) = with(binding){
        val filename = "about_content.html"
        webviewAbout.getSettings().setJavaScriptEnabled(true);
        webviewAbout.loadUrl("file:///android_asset/" + filename);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}