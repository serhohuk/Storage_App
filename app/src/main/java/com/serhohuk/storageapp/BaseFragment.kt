package com.serhohuk.storageapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

open class BaseFragment : Fragment() {

    fun navigateToOtMFragment(fm: FragmentManager) {
        replaceFragmentWithBackStack(fm, OtMFragment())
    }

    private fun replaceFragmentWithBackStack(fm: FragmentManager, fragment: Fragment) {
        fm.beginTransaction()
            .replace(R.id.container, fragment, fragment.javaClass.simpleName)
            .addToBackStack(fragment.javaClass.simpleName)
            .commit()
    }

}