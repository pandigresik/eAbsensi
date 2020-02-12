package com.asligresik.absensi.ui.submission.ijin

import com.asligresik.absensi.data.repository.SubmissionRepository
import com.asligresik.absensi.ui.submission.SubmissionViewModel

class IjinViewModel(private val submissionRepository: SubmissionRepository) : SubmissionViewModel(submissionRepository) {
    override val typeSubmission: String = "IJ"
}