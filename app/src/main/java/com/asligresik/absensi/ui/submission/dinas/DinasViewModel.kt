package com.asligresik.absensi.ui.submission.cuti

import com.asligresik.absensi.data.repository.SubmissionRepository
import com.asligresik.absensi.ui.submission.SubmissionViewModel

class DinasViewModel(private val submissionRepository: SubmissionRepository) : SubmissionViewModel(submissionRepository) {
    override val typeSubmission: String = "DL"
}