package com.asligresik.absensi.ui.submission.ijin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.asligresik.absensi.R
import com.asligresik.absensi.data.repository.SubmissionRepository
import com.asligresik.absensi.ui.submission.FormSubmissionFragment
import com.asligresik.absensi.ui.submission.SubmissionViewModelFactory
import kotlinx.android.synthetic.main.fragment_form_submission_partial.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val STATE_FORM = "param1"
private const val ID_FORM = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FormIjinFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FormIjinFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FormIjinFragment : FormSubmissionFragment() {
    override val title: String = "Pengajuan Ijin"
    override fun setViewModel(submissionRepository: SubmissionRepository){
        submissionViewModel =
            ViewModelProviders.of(this,
                SubmissionViewModelFactory(
                    submissionRepository
                )
            ).get(IjinViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form_submission, container, false)
    }

    override protected fun save() {
        submissionViewModel.create(etStartDate.text.toString(),etEndDate.text.toString(),etDescription.text.toString())
    }
}
