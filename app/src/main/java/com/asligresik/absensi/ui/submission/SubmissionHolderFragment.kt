package com.asligresik.absensi.ui.submission

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.asligresik.absensi.MainActivity
import com.asligresik.absensi.R
import com.asligresik.absensi.adapters.SubmissionAdapter
import com.asligresik.absensi.data.datasource.SubmissionDataSource
import com.asligresik.absensi.data.repository.SubmissionRepository
import kotlinx.android.synthetic.main.fragment_recycleview.*

/**
 * A placeholder fragment containing a simple view.
 */
open class SubmissionHolderFragment : Fragment() {
    protected var sectionNumber : Int = 1
    protected val listSubmissionAdapter = SubmissionAdapter()
    internal lateinit var submissionViewModel: SubmissionViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val submissionDataSource = SubmissionDataSource((activity as? MainActivity)!!.getApiService())
        val submissionRepository = SubmissionRepository(submissionDataSource)
        setViewModel(submissionRepository)
        sectionNumber = arguments?.getInt(ARG_SECTION_NUMBER) ?: 1
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
        val root = inflater.inflate(R.layout.fragment_recycleview, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showRecyclerList()
        submissionViewModel.loading.observe(this, Observer {
                it -> showLoading(it)
        })
        when(sectionNumber){
            1 -> {
                submissionViewModel.loadData("Active")
                submissionViewModel.listSubmissionActive.observe(this, Observer{
                    if(it.submission != null){
                        listSubmissionAdapter.setDatas(it.submission)
                    }
                })
            }
            2 -> {
                submissionViewModel.loadData("History")
                submissionViewModel.listSubmission.observe(this, Observer{
                    if(it.submission != null){
                        listSubmissionAdapter.setDatas(it.submission)
                    }
                })
            }
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(ARG_SECTION_NUMBER, sectionNumber)
    }

    protected open fun showRecyclerList(){
        rvSubmissionList.layoutManager = LinearLayoutManager(context)
        rvSubmissionList.setHasFixedSize(true)
        rvSubmissionList.adapter = listSubmissionAdapter
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
        fun newInstance(sectionNumber: Int): SubmissionHolderFragment {
            return SubmissionHolderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    protected fun showLoading(state: Boolean){
        if(state) {
            progressBar.visibility = View.VISIBLE
        }else{
            progressBar.visibility = View.GONE
        }
    }
}