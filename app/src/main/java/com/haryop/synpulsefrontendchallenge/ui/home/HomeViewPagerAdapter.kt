package com.haryop.synpulsefrontendchallenge.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class HomeViewPagerAdapter(fm: FragmentManager, listFrag: List<Fragment>, listTitleFrag: List<String>, behaviour: Int) :
    FragmentStatePagerAdapter(fm, behaviour) {

    var listFrag = listFrag
    var listTitleFrag = listTitleFrag

    override fun getItem(position: Int): Fragment {
        return listFrag.get(position)
    }

    override fun getCount(): Int {
        return listFrag.count()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return listTitleFrag.get(position)
    }
}