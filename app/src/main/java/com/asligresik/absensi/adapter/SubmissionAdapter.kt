package com.asligresik.absensi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asligresik.absensi.R
import com.asligresik.absensi.data.model.Submission
import kotlinx.android.synthetic.main.item_submission.view.*

class SubmissionAdapter : RecyclerView.Adapter<SubmissionAdapter.viewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null
    private var listSubmission: List<Submission> = ArrayList<Submission>()

    fun setDatas(listSubmission: List<Submission>) {
        this.listSubmission = listSubmission
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
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
                val description = """
                    Periode ${submission.startDate} sd ${submission.endDate}
                    ${submission.description} 
                """.trimIndent()
                tvDescription.text = description
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
        fun onItemClicked(submission: Submission)
        //fun onFavoriteClick(submission: Submission, position: Int)
    }
}
