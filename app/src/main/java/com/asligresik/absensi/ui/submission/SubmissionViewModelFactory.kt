package com.asligresik.absensi.ui.submission

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.asligresik.absensi.data.repository.SubmissionRepository
import com.asligresik.absensi.ui.submission.approval.ApprovalViewModel
import com.asligresik.absensi.ui.submission.cuti.CutiViewModel
import com.asligresik.absensi.ui.submission.cuti.DinasViewModel
import com.asligresik.absensi.ui.submission.ijin.IjinViewModel
import com.asligresik.absensi.ui.submission.sakit.SakitViewModel

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class SubmissionViewModelFactory(private val submissionRepository: SubmissionRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IjinViewModel::class.java)) {
            return IjinViewModel(
                submissionRepository
            ) as T
        }
        if (modelClass.isAssignableFrom(DinasViewModel::class.java)) {
            return DinasViewModel(
                submissionRepository
            ) as T
        }
        if (modelClass.isAssignableFrom(CutiViewModel::class.java)) {
            return CutiViewModel(
                submissionRepository
            ) as T
        }
        if (modelClass.isAssignableFrom(SakitViewModel::class.java)) {
            return SakitViewModel(
                submissionRepository
            ) as T
        }
        if (modelClass.isAssignableFrom(SubmissionViewModel::class.java)) {
            return SubmissionViewModel(
                submissionRepository
            ) as T
        }
        if (modelClass.isAssignableFrom(ApprovalViewModel::class.java)) {
            return ApprovalViewModel(
                submissionRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
