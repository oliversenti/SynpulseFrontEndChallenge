package com.haryop.synpulsefrontendchallenge.ui

import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.haryop.synpulsefrontendchallenge.R
import com.haryop.synpulsefrontendchallenge.databinding.ActivitySplashScreenBinding
import com.haryop.synpulsefrontendchallenge.ui.home.MainActivity
import com.haryop.synpulsefrontendchallenge.utils.BaseActivityBinding
import com.haryop.synpulsefrontendchallenge.utils.ConstantsObj
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashScreenActivity: BaseActivityBinding<ActivitySplashScreenBinding>() {

    val activityScope = CoroutineScope(Dispatchers.Main)

    override val bindingInflater: (LayoutInflater) -> ActivitySplashScreenBinding
        get() = ActivitySplashScreenBinding::inflate

    override fun setupView(binding: ActivitySplashScreenBinding) {
        setupAction(binding.root)
    }

    fun setupAction(view: View) = with(binding) {
        activityScope.launch {
            delay(2000)
            var intent= Intent()

            Log.e("splashscreen", "isLogin(): " + isLogin())
            if (isLogin()){
                intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            }else{
                intent = Intent(this@SplashScreenActivity, LandingActivity::class.java)
            }
            intent.putExtra(
                ConstantsObj.KEY_TARGET_ACTIVITY,
                ConstantsObj.VALUE_TITLESCREEN_ACTIVITY
            )
            startActivity(intent)
            finish()
        }
    }

    fun isLogin():Boolean{
        val sharedPref = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        var islogin = sharedPref.getString(ConstantsObj.KEY_ISLOGIN, "")
        Log.e("splashscreen", "isLogin: " + islogin)

        if (islogin.isNullOrEmpty() || islogin.equals("")){
            return false
        }else{
            return true
        }
    }

}