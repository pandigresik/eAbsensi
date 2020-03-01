package com.asligresik.absensi.ui.submission.approval

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.asligresik.absensi.MainActivity
import com.asligresik.absensi.R
import com.asligresik.absensi.data.datasource.SubmissionDataSource
import com.asligresik.absensi.data.repository.SubmissionRepository
import com.asligresik.absensi.ui.submission.SubmissionFragment
import com.asligresik.absensi.ui.submission.SubmissionPagerAdapter
import com.asligresik.absensi.ui.submission.SubmissionViewModelFactory
import kotlinx.android.synthetic.main.fragment_submission.*

class ApprovalFragment : SubmissionFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setAdapterFragment(): SubmissionPagerAdapter {
        return ApprovalPagerAdapter(context!!,childFragmentManager)
    }
}