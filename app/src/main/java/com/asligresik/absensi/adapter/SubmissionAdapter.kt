package com.asligresik.absensi.adapters

import android.util.Log
import android.util.SparseIntArray
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asligresik.absensi.R
import com.asligresik.absensi.data.model.Submission
import kotlinx.android.synthetic.main.item_submission.view.*

class SubmissionAdapter : RecyclerView.Adapter<SubmissionAdapter.viewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null
    private var listSubmission: List<Submission> = ArrayList<Submission>()
    var canApprove = false
    fun setDatas(listSubmission: List<Submission>) {

        this.listSubmission = listSubmission
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setItemProcessing(itemProcessing: HashMap<Int, Int>){
        Log.e("setItemProcessing",itemProcessing.toString());
        itemProcessing.map<Int, Int, Unit> {
            Log.e("setItemProcessing2",it.key.toString());
            listSubmission.get(it.key).status =
            when(it.value){
                0 -> 'V'
                else -> 'A'
            }
            notifyItemChanged(it.key)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_submission, parent, false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return listSubmission.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind(listSubmission[position],position)
    }

    inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(submission: Submission, position: Int) {
            with(itemView) {
                tvTitle.text = submission.number
                var _isActive: Boolean = submission.status!!.equals('N',true)
                val description = """
                    Periode ${submission.startDate} sd ${submission.endDate}
                    ${submission.description} 
                """.trimIndent()
                tvDescription.text = description
                if(canApprove){
                    if(_isActive){
                        itemView.blockApproval.visibility = VISIBLE
                        itemView.btnApprove.setOnClickListener {
                            onItemClickCallback?.onApproveBtnClick(submission, position)
                        }
                        itemView.btnReject.setOnClickListener {
                            onItemClickCallback?.onRejectBtnClick(submission, position)
                        }
                    }
                }

                if(!_isActive){
                    itemView.imgStatus.visibility = View.VISIBLE
                    when(submission.status){
                        'A' -> itemView.imgStatus.setImageResource(R.drawable.approved)
                        else -> itemView.imgStatus.setImageResource(R.drawable.rejected)
                    }
                }
                /*Glide.with(itemView.context)
                    .load(Constant.PATH_POSTER + "/w185" + submission.posterPath)
                    .apply(RequestOptions().override(300, 300))
                    .into(img_submission)

                tv_short_description.text = submission.description
                txt_title.text = submission.title
                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(submission) }
                itemView.btnFavorite.setOnClickListener { onItemClickCallback?.onFavoriteClick(submission,position) }
                itemView.setOnLongClickListener {
                    onItemClickCallback?.onItemLongClicked(submission, position)
                    return@setOnLongClickListener true
                }

                 */
            }
        }
    }

    interface OnItemClickCallback {
        fun onApproveBtnClick(submission: Submission, position: Int)
        fun onRejectBtnClick(submission: Submission, position: Int)
    }
}
