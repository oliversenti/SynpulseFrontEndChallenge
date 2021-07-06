package com.haryop.synpulsefrontendchallenge.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.haryop.synpulsefrontendchallenge.R
import com.haryop.synpulsefrontendchallenge.databinding.FragmentProfileBinding
import com.haryop.synpulsefrontendchallenge.utils.BaseFragmentBinding
import com.haryop.synpulsefrontendchallenge.utils.ConstantsObj

class ProfileFragment : BaseFragmentBinding<FragmentProfileBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProfileBinding
        get() = FragmentProfileBinding::inflate

    lateinit var viewbinding: FragmentProfileBinding
    override fun setupView(binding: FragmentProfileBinding) {
        viewbinding = binding
        setUpAction(binding.root)
    }

    fun setUpAction(view: View) = with(viewbinding) {
        about.setOnClickListener { openAbout()}
        signOut.setOnClickListener { onSignOut(view) }
    }

    fun openAbout(){
        findNavController().navigate(R.id.action_profile_to_about)
    }

    fun onSignOut(view: View) {
        //Instantiate builder variable
        val builder = AlertDialog.Builder(view.context)

        // set title
        builder.setTitle("Sign Out")

        //set content area
        builder.setMessage("Sign out from this app now?")

        //set negative button
        builder.setPositiveButton(
            "Sign Out"
        ) { dialog, id ->
            // User clicked Update Now button
            var intent = Intent(activity, LandingActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra(ConstantsObj.KEY_TARGET_ACTIVITY, ConstantsObj.VALUE_TITLESCREEN_ACTIVITY )
            activity?.startActivity(intent)

        }

        //set positive button
        builder.setNegativeButton(
            "Cancel"
        ) { dialog, id ->
            // User cancelled the dialog
        }

        builder.show()
    }

}