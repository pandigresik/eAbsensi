package com.asligresik.absensi.data.response

import com.asligresik.absensi.data.model.DataLogin
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status")
    var status: Int? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("data")
    var data: DataLogin? = null
)