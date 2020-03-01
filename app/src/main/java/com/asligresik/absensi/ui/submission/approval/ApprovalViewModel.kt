package com.asligresik.absensi.ui.submission.approval

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.asligresik.absensi.data.repository.SubmissionRepository
import com.asligresik.absensi.ui.submission.SubmissionViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApprovalViewModel(private val submissionRepository: SubmissionRepository) : SubmissionViewModel(submissionRepository) {
    /* state 0 for rejected, 1 for approved, 2 for processing */
    private val _approvalProcess = MutableLiveData<HashMap<Int,Int>>()
    val processingItemState: LiveData<HashMap<Int,Int>> = _approvalProcess

    init {
        _approvalProcess.value = HashMap()
    }
    fun approveReject(position: Int,id: Int, statusApproval: String, reason: String){
        loading.postValue(true)
        _approvalProcess.value?.put(position,value = 2)
        submissionJob = viewModelScope.launch(Dispatchers.Main) {
            val result  = submissionRepository.approveReject(id, statusApproval, reason)
            _approvalProcess.value?.put(position,statusApproval.toInt())
            loading.postValue(false)
            /*sementara pakai cara ini dulu */
            super.loadData("Approval")
        }
    }
}