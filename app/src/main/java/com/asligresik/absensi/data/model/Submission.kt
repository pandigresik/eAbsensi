package com.asligresik.absensi.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Submission(
    @SerializedName("user_id") var userId: Int? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("start_date") var startDate: String? = null,
    @SerializedName("end_date") var endDate: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("status") var status: Char? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("number") var number: String? = null
) : Parcelable {
    constructor(
        userId: Int,
        type: String,
        startDate: String,
        endDate: String,
        description: String,
        status: Char
        ) : this(userId, type, startDate,endDate, description, status,id = null,number = null)
}