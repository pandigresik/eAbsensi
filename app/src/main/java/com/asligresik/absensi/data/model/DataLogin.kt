package com.asligresik.absensi.data.model

import com.google.gson.annotations.SerializedName

data class DataLogin(
    @SerializedName("user")
    var user: LoggedInUser? = null,
    @SerializedName("token")
    var token: String? = null
)