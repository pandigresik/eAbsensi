package com.asligresik.absensi.data.response

import com.google.gson.annotations.SerializedName
import java.util.*

open class BaseResponse(
    @SerializedName("status")
    var status: Int? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("data")
    var data: Objects? = null
)