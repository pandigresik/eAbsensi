package com.asligresik.absensi.ui.submission

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.asligresik.absensi.MainActivity
import com.asligresik.absensi.R
import com.asligresik.absensi.data.datasource.SubmissionDataSource
import com.asligresik.absensi.data.repository.SubmissionRepository
import com.asligresik.absensi.ui.login.afterTextChanged
import kotlinx.android.synthetic.main.fragment_form_submission.*
import kotlinx.android.synthetic.main.fragment_form_submission_footer.*
import kotlinx.android.synthetic.main.fragment_form_submission_partial.*
import java.text.SimpleDateFormat
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val STATE_FORM = "param1"
private const val ID_FORM = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FormCutiFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FormCutiFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
open class FormSubmissionFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var calendar = Calendar.getInstance()
    internal lateinit var submissionViewModel: SubmissionViewModel
    open val title = "Pengajuan "
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val submissionDataSource = SubmissionDataSource((activity as? MainActivity)!!.getApiService())
        val submissionRepository = SubmissionRepository(submissionDataSource)
        setViewModel(submissionRepository)
        arguments?.let {
            param1 = it.getString(STATE_FORM)
            param2 = it.getString(ID_FORM)
        }
    }
    open fun setViewModel(submissionRepository: SubmissionRepository){
        submissionViewModel =
            ViewModelProviders.of(this,
                SubmissionViewModelFactory(
                    submissionRepository
                )
            ).get(SubmissionViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form_submission, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        titleForm.text = title
        val dataSetListener =
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateText(view?.tag)
            }

        val datePickerDialog = View.OnClickListener { v ->
            val picker = DatePickerDialog(context!!,dataSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH))
            picker.datePicker.tag = v?.id
            picker.show()
        }
        etStartDate.setOnClickListener(datePickerDialog)
        etEndDate.setOnClickListener(datePickerDialog)
        btnSave.setOnClickListener {
            save()
        }
        etStartDate.afterTextChanged {
            submissionViewModel.formDataChange(etStartDate.text.toString(),etEndDate.text.toString(),etDescription.text.toString())
        }
        etEndDate.afterTextChanged {
            submissionViewModel.formDataChange(etStartDate.text.toString(),etEndDate.text.toString(),etDescription.text.toString())
        }
        etDescription.afterTextChanged {
            submissionViewModel.formDataChange(etStartDate.text.toString(),etEndDate.text.toString(),etDescription.text.toString())
        }

        submissionViewModel.submissionFormState.observe(this, androidx.lifecycle.Observer {
            val submissionState = it ?: return@Observer
            btnSave.isEnabled = submissionState.isDataValid
            if(submissionState.startDateError != null){
                etStartDate.error = getString(submissionState.startDateError)
            }
            if(submissionState.endDateError != null){
                etEndDate.error = getString(submissionState.endDateError)
            }
            if(submissionState.descriptionError != null){
                etDescription.error = getString(submissionState.descriptionError)
            }
        })

        submissionViewModel.submissionResult.observe(this, androidx.lifecycle.Observer {
            if (it.success != null){
                //Snackbar.make(R.id.footer_layout,"Pengajuan berhasil dibuat",Snackbar.LENGTH_LONG).show()
                Toast.makeText(context,"Pengajuan ${it.submission?.number} berhasil dibuat",Toast.LENGTH_LONG).show()
                findNavController().popBackStack()
            }
        })

        submissionViewModel.loading.observe(this, androidx.lifecycle.Observer {
            if(it){
                btnSave.visibility = View.INVISIBLE
                loading.visibility = View.VISIBLE
            }else{
                btnSave.visibility = View.VISIBLE
                loading.visibility = View.INVISIBLE
            }
        })
    }

    protected open fun save() {
        submissionViewModel.create(etStartDate.text.toString(),etEndDate.text.toString(),etDescription.text.toString())
    }

    private fun updateDateText(tag: Any?) {
        val myFormat = "yyyy-MM-dd" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        val dateSelected = sdf.format(calendar.time)
        when(tag){
            etStartDate.id -> etStartDate.setText(dateSelected)
            etEndDate.id -> etEndDate.setText(dateSelected)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FormCutiFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FormSubmissionFragment().apply {
                arguments = Bundle().apply {
                    putString(STATE_FORM, param1)
                    putString(ID_FORM, param2)
                }
            }
    }
}
