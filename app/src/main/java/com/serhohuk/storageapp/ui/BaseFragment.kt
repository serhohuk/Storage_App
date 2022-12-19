package com.serhohuk.storageapp.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.serhohuk.storageapp.R
import com.serhohuk.storageapp.ui.otm.OtMFragment
import com.serhohuk.storageapp.ui.otm.PersonsFamilyFragment

open class BaseFragment : Fragment() {

    fun navigateToOtMFragment(fm: FragmentManager) {
        replaceFragmentWithBackStack(fm, OtMFragment())
    }

    fun navigateToPersonsFragment(fm: FragmentManager, familyId: Int) {
        replaceFragmentWithBackStack(fm , PersonsFamilyFragment.newInstance(familyId))
    }

    private fun replaceFragmentWithBackStack(fm: FragmentManager, fragment: Fragment) {
        fm.beginTransaction()
            .replace(R.id.container, fragment, fragment.javaClass.simpleName)
            .addToBackStack(fragment.javaClass.simpleName)
            .commit()
    }

}