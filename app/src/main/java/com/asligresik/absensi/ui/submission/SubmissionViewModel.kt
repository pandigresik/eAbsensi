package com.asligresik.absensi.ui.submission

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asligresik.absensi.R
import com.asligresik.absensi.data.Result
import com.asligresik.absensi.data.repository.SubmissionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class SubmissionViewModel(private val submissionRepository: SubmissionRepository) : ViewModel() {
    protected var submissionJob: Job? = null
    private val _submissionForm = MutableLiveData<SubmissionFormState>()
    val submissionFormState: LiveData<SubmissionFormState> = _submissionForm
    val loading = MutableLiveData<Boolean>()
    private val _submissionResult = MutableLiveData<SubmissionResult>()
    val submissionResult: LiveData<SubmissionResult> = _submissionResult
    private val _listSubmission = MutableLiveData<ListSubmissionResult>()
    val listSubmission: LiveData<ListSubmissionResult> = _listSubmission
    private val _listSubmissionActive = MutableLiveData<ListSubmissionResult>()
    val listSubmissionActive: LiveData<ListSubmissionResult> = _listSubmissionActive
    private val _listSubmissionApproval = MutableLiveData<ListSubmissionResult>()
    val listSubmissionApproval: LiveData<ListSubmissionResult> = _listSubmissionApproval
    open val typeSubmission: String = "CT"

    fun create(startDate: String, endDate: String, description: String){
        loading.value = true
        submissionJob = viewModelScope.launch(Dispatchers.Main) {
            val result  = submissionRepository.create(startDate, endDate, description,typeSubmission)
            if(result is Result.Success){
                _submissionResult.value =
                    SubmissionResult(
                        success = R.string.save_data_success,
                        submission = result.data
                    )
            }else{
                _submissionResult.value =
                    SubmissionResult(error = R.string.failed_save_data)
            }
            loading.value = false
        }
    }

    fun loadData(status: String){
        loading.value = true
        submissionJob = viewModelScope.launch(Dispatchers.Main) {
            val result  = submissionRepository.loadData(status,typeSubmission)
            if(result is Result.Success){
                when(status){
                    "Active" -> _listSubmissionActive.value =
                        ListSubmissionResult(
                            success = R.string.save_data_success,
                            submission = result.data
                        )
                    "Approval" -> _listSubmissionApproval.value =
                        ListSubmissionResult(
                            success = R.string.save_data_success,
                            submission = result.data
                        )
                    else -> _listSubmission.value =
                        ListSubmissionResult(
                            success = R.string.save_data_success,
                            submission = result.data
                        )
                }
            }else{
                when(status){
                    "Active" -> _listSubmissionActive.value =
                        ListSubmissionResult(
                            error = R.string.failed_save_data
                        )
                    "Approval" -> _listSubmissionApproval.value =
                        ListSubmissionResult(
                            error = R.string.failed_save_data
                        )
                    else -> _listSubmission.value =
                        ListSubmissionResult(
                            error = R.string.failed_save_data
                        )
                }
            }
            loading.value = false
        }
    }

    fun formDataChange(startDate: String, endDate: String, description: String){
        if(!dateValid(startDate)){
            _submissionForm.value = SubmissionFormState(startDateError = R.string.date_not_valid)
        }else if(!dateValid(endDate)){
            _submissionForm.value = SubmissionFormState(endDateError = R.string.date_not_valid)
        }else if(!descriptionValid(description)){
            _submissionForm.value = SubmissionFormState(descriptionError = R.string.description_not_valid)
        }else{
            _submissionForm.value = SubmissionFormState(isDataValid = true)
        }
    }
    
    private fun dateValid(strDate: String):Boolean{
        return strDate.trim().isNotEmpty()
    }

    private fun descriptionValid(description: String):Boolean{
        return description.trim().isNotEmpty()
    }
    
    override fun onCleared() {
        super.onCleared()
        submissionJob?.cancel()
    }
}