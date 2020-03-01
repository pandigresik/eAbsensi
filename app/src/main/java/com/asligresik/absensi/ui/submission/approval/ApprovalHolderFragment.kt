package com.asligresik.absensi.ui.submission.approval

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.asligresik.absensi.R
import com.asligresik.absensi.adapters.SubmissionAdapter
import com.asligresik.absensi.data.model.Submission
import com.asligresik.absensi.data.repository.SubmissionRepository
import com.asligresik.absensi.ui.submission.SubmissionHolderFragment
import com.asligresik.absensi.ui.submission.SubmissionViewModel
import com.asligresik.absensi.ui.submission.SubmissionViewModelFactory
import com.asligresik.absensi.utils.Helper
import com.google.android.material.snackbar.Snackbar
import java.text.FieldPosition

/**
 * A placeholder fragment containing a simple view.
 */
class ApprovalHolderFragment : SubmissionHolderFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        showRecyclerList()
        (submissionViewModel as ApprovalViewModel).loading.observe(this, Observer { it ->
            showLoading(it)
        })
        when (sectionNumber) {
            1 -> {
                submissionViewModel.loadData("Approval")
                submissionViewModel.listSubmissionApproval.observe(this, Observer {
                    if (it.submission != null) {
                        listSubmissionAdapter.canApprove = true
                        listSubmissionAdapter.setDatas(it.submission)
                    }
                })
            }
            2 -> {
                submissionViewModel.loadData("ApprovalHistory")
                submissionViewModel.listSubmission.observe(this, Observer {
                    if (it.submission != null) {
                        listSubmissionAdapter.canApprove = false
                        listSubmissionAdapter.setDatas(it.submission)
                    }
                })
            }
        }

        (submissionViewModel as ApprovalViewModel).processingItemState.observe(this, Observer {
            Log.e("processingItemStateh",it.toString())
            if (it.isEmpty()) {
                listSubmissionAdapter.setItemProcessing(it)
            }
        })
    }

    override fun showRecyclerList() {
        super.showRecyclerList()
        listSubmissionAdapter.setOnItemClickCallback(onItemClickCallback = object :
            SubmissionAdapter.OnItemClickCallback {
            override fun onApproveBtnClick(submission: Submission, position: Int) {
                Helper.showAlertDialog(
                    context!!, "Konfirmasi Approve", "Apakah anda yakin akan melakukan approval ?",
                    CallbackAlertButton(submission, submissionViewModel,position)
                ).show()
            }

            override fun onRejectBtnClick(submission: Submission, position: Int) {
                val view = LayoutInflater.from(context).inflate(R.layout.dialog_reject, null)
                Helper.showPromptDialog(
                    context!!,
                    "Konfirmasi Approve",
                    "Apakah anda yakin akan melakukan approval ?",
                    view,
                    CallbackPromptButton(submission, submissionViewModel, view, position)
                ).show()
            }
        })
    }

    override fun setViewModel(submissionRepository: SubmissionRepository) {
        submissionViewModel =
            ViewModelProviders.of(
                this,
                SubmissionViewModelFactory(
                    submissionRepository
                )
            ).get(ApprovalViewModel::class.java)
    }

    class CallbackAlertButton(
        val submission: Submission,
        val submissionViewModel: SubmissionViewModel,
        val position: Int
    ) : Helper.OnCallbackAlertDialog {
        override fun positiveBtnClick() {
            (submissionViewModel as ApprovalViewModel).approveReject(
                position,submission.id!!, "1",
                reason = ""
            )
        }
    }

    class CallbackPromptButton(
        val submission: Submission,
        val submissionViewModel: SubmissionViewModel,
        val view: View,
        val position: Int
    ) : Helper.OnCallbackPromptDialog {
        @SuppressLint("ResourceType")
        override fun positiveBtnClick(view: View) {
            val reason = view.findViewById<EditText>(R.id.etReasonReject)
            val rejectReason = reason.text.toString()
            if(!rejectReason.isEmpty()){
                (submissionViewModel as ApprovalViewModel).approveReject(
                    position,submission.id!!, "0",
                    reason = rejectReason
                )
            }else{
                val mLayout =  LayoutInflater.from(view.context).inflate(R.id.contentMainLayout, null) as ConstraintLayout
                Snackbar.make(mLayout,"Alasan harus diisi",Snackbar.LENGTH_SHORT).show()
            }
        }

        override fun negativeBtnClick() {

        }
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): ApprovalHolderFragment {
            return ApprovalHolderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}