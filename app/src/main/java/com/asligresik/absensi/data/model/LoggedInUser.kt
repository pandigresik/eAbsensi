package com.asligresik.absensi.data.model

import com.google.gson.annotations.SerializedName

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    @SerializedName("id")
    var userId: String? = null,
    @SerializedName("name")
    var displayName: String? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("token")
    var token: String? = null,
    @SerializedName("status")
    var isLogged: Boolean = false
)
