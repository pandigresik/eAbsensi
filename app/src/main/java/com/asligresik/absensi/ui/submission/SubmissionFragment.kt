package com.asligresik.absensi.ui.submission

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.asligresik.absensi.MainActivity
import com.asligresik.absensi.R
import com.asligresik.absensi.data.datasource.SubmissionDataSource
import com.asligresik.absensi.data.repository.SubmissionRepository
import com.asligresik.absensi.ui.submission.SubmissionPagerAdapter
import com.asligresik.absensi.ui.submission.SubmissionViewModelFactory

import kotlinx.android.synthetic.main.fragment_submission.*

open class SubmissionFragment : Fragment() {

    private lateinit var submissionViewModel: SubmissionViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_submission, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view_pager.adapter = setAdapterFragment()
        tabLayout.setupWithViewPager(view_pager)
    }

    open fun setAdapterFragment(): SubmissionPagerAdapter {
        return SubmissionPagerAdapter(context!!,childFragmentManager)
    }
}