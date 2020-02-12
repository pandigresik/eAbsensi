package com.asligresik.absensi.ui.submission.sakit

import com.asligresik.absensi.data.repository.SubmissionRepository
import com.asligresik.absensi.ui.submission.SubmissionViewModel

class SakitViewModel(private val submissionRepository: SubmissionRepository) : SubmissionViewModel(submissionRepository) {
    override val typeSubmission: String = "SK"
}