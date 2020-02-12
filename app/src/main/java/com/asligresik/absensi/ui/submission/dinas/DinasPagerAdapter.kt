package com.asligresik.absensi.ui.submission.dinas

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.asligresik.absensi.ui.submission.SubmissionPagerAdapter


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class DinasPagerAdapter(private val context: Context, fm: FragmentManager)
    : SubmissionPagerAdapter(context,fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a SubmissionholderFragment (defined as a static inner class below).
        return DinasHolderFragment.newInstance(position + 1)
    }
}