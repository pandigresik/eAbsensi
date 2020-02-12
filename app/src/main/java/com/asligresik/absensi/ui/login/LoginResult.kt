package com.asligresik.absensi.ui.login

import com.asligresik.absensi.data.model.LoggedInUser

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null,
    val user: LoggedInUser? = null
)
