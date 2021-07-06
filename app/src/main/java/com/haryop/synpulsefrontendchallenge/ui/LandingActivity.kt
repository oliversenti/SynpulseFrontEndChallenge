package com.haryop.synpulsefrontendchallenge.ui

import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.haryop.synpulsefrontendchallenge.R
import com.haryop.synpulsefrontendchallenge.databinding.ActivityLandingBinding
import com.haryop.synpulsefrontendchallenge.utils.BaseActivityBinding
import com.haryop.synpulsefrontendchallenge.utils.ConstantsObj
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LandingActivity : BaseActivityBinding<ActivityLandingBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityLandingBinding
        get() = ActivityLandingBinding::inflate

    var target_activity = 0
    override fun setupView(binding: ActivityLandingBinding) {
        target_activity = intent.getIntExtra(ConstantsObj.KEY_TARGET_ACTIVITY, 0)
        when (target_activity) {
            ConstantsObj.VALUE_TITLESCREEN_ACTIVITY -> setupAction_TitleScreen(binding.root)
            ConstantsObj.VALUE_WELCOMESEARCH_ACTIVITY -> setupAction_WelcomSearch(binding.root)
            ConstantsObj.VALUE_BROWSECOMP_ACTIVITY -> setupAction_InnerPage(binding.root, R.navigation.browsecomp_nav_graph)
            ConstantsObj.VALUE_PROFILE_ACTIVITY -> setupAction_InnerPage(binding.root, R.navigation.profile_nav_graph)
        }
    }

    fun setupAction_TitleScreen(view: View) = with(binding) {
        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_landing_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        navController.setGraph(R.navigation.titlescreen_nav_graph)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.titlescreenFragment) {
                landingToolbar.visibility = View.GONE
            } else {
                landingToolbar.visibility = View.VISIBLE
            }
        }

        val appBarConfiguration: AppBarConfiguration = AppBarConfiguration(navController.graph)
        landingToolbar.setupWithNavController(navController, appBarConfiguration)
        landingToolbar.visibility = View.GONE
    }

    fun setupAction_WelcomSearch(view: View) = with(binding) {
        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_landing_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        navController.setGraph(R.navigation.browsecomp_nav_graph)

        val appBarConfiguration: AppBarConfiguration = AppBarConfiguration(navController.graph)
        landingToolbar.setupWithNavController(navController, appBarConfiguration)
    }

    fun setupAction_InnerPage(view: View, graphid:Int) = with(binding) {
        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_landing_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        navController.setGraph(graphid)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            landingToolbar.title = destination.label
        }

        setSupportActionBar(landingToolbar);
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        landingToolbar.setNavigationOnClickListener {
            if (navController.currentDestination?.id == navController.graph.startDestination) {
                finish()
            } else {
                navController.popBackStack()
            }
        }
    }

    val activityScope = CoroutineScope(Dispatchers.Main)
    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (isTaskRoot && target_activity == ConstantsObj.VALUE_WELCOMESEARCH_ACTIVITY) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed()
                return
            }

            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

            activityScope.launch {
                delay(2000)
                doubleBackToExitPressedOnce = false
            }
        } else {
            super.onBackPressed()
        }
    }

}