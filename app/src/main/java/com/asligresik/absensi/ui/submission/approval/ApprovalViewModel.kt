package com.asligresik.absensi.ui.submission.approval

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.asligresik.absensi.R
import com.asligresik.absensi.data.Result
import com.asligresik.absensi.data.repository.SubmissionRepository
import com.asligresik.absensi.ui.submission.SubmissionFormState
import com.asligresik.absensi.ui.submission.SubmissionResult
import com.asligresik.absensi.ui.submission.SubmissionViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApprovalViewModel(private val submissionRepository: SubmissionRepository) : SubmissionViewModel(submissionRepository) {
    /* state 0 for rejected, 1 for approved, 2 for processing */
    private val _approvalProcess = MutableLiveData<HashMap<Int,Int>>()
    val processingItemState: LiveData<HashMap<Int,Int>> = _approvalProcess
    fun approveReject(position: Int,id: Int, statusApproval: String, reason: String){
        _approvalProcess.value?.set(position,value = 2)
        submissionJob = viewModelScope.launch(Dispatchers.Main) {
            val result  = submissionRepository.approveReject(id, statusApproval, reason)
            _approvalProcess.value?.set(position,statusApproval.toInt())
        }
    }
}