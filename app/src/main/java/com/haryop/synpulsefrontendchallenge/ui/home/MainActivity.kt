package com.haryop.synpulsefrontendchallenge.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.haryop.synpulsefrontendchallenge.ui.AboutFragment
import com.haryop.synpulsefrontendchallenge.ui.LandingActivity
import com.haryop.synpulsefrontendchallenge.R
import com.haryop.synpulsefrontendchallenge.databinding.ActivityMainBinding
import com.haryop.synpulsefrontendchallenge.ui.home.dashboard.DashboardFragment
import com.haryop.synpulsefrontendchallenge.utils.BaseActivityBinding
import com.haryop.synpulsefrontendchallenge.utils.ConstantsObj
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : BaseActivityBinding<ActivityMainBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun setupView(binding: ActivityMainBinding) {
        setUpAction(binding.root)
    }

    fun setUpAction(view: View) = with(binding) {
        setupActionbar()
        setupViewPager()
    }

    fun setupActionbar(){
        var actionbar = supportActionBar
        actionbar?.title = resources.getString(R.string.synpulse)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.action_profile -> {
                openAboutActivity()
            }
            R.id.action_search -> {
                openBrowseFIActivity()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun openAboutActivity() {
        var intent = Intent(this, LandingActivity::class.java)
        intent.putExtra(ConstantsObj.KEY_TARGET_ACTIVITY, ConstantsObj.VALUE_PROFILE_ACTIVITY )
        startActivity(intent)
    }

    fun openBrowseFIActivity() {
        var intent = Intent(this, LandingActivity::class.java)
        intent.putExtra(ConstantsObj.KEY_TARGET_ACTIVITY, ConstantsObj.VALUE_BROWSECOMP_ACTIVITY )
        startActivity(intent)
    }

    lateinit var dashboardFragment: DashboardFragment
    lateinit var portfolioFragment: PortfolioFragment
    fun setupViewPager() = with(binding) {
        dashboardFragment =
            DashboardFragment()
        portfolioFragment = PortfolioFragment()

        val listFrag = arrayListOf<Fragment>()
        listFrag.add(dashboardFragment) // di inject pakek dagger manggilnya
        listFrag.add(portfolioFragment) // di inject pakek dagger manggilnya

        val listTitleFrag = arrayListOf<String>()
        listTitleFrag.add(resources.getString(R.string.dashboard_title_toolbar))
        listTitleFrag.add(resources.getString(R.string.portfolio_title_toolbar))

        val viewPagerAdapter = HomeViewPagerAdapter(
            supportFragmentManager,
            listFrag,
            listTitleFrag,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )

        viewPagerHome.adapter = viewPagerAdapter
        tabLayoutHome.setupWithViewPager(viewPagerHome)

    }

    val activityScope = CoroutineScope(Dispatchers.Main)
    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
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
    }
}