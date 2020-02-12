package com.asligresik.absensi.data.response

import com.asligresik.absensi.data.model.Submission
import com.google.gson.annotations.SerializedName

data class ListSubmissionResponse(
    @SerializedName("status")
    var status: Int? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("data")
    var data: List<Submission>? = null
)