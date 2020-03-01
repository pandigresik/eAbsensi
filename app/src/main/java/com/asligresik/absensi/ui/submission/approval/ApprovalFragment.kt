package com.asligresik.absensi.ui.submission.approval

import android.os.Bundle
import android.view.View
import com.asligresik.absensi.ui.submission.SubmissionFragment
import com.asligresik.absensi.ui.submission.SubmissionPagerAdapter
import kotlinx.android.synthetic.main.fragment_submission.*

class ApprovalFragment : SubmissionFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(addFormButton) { addFormButton.hide() }
    }

    override fun setAdapterFragment(): SubmissionPagerAdapter {
        return ApprovalPagerAdapter(context!!,childFragmentManager)
    }
}