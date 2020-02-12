package com.asligresik.absensi.ui.submission

import com.asligresik.absensi.data.model.Submission

/**
 * Authentication result : success (user details) or error message.
 */
data class ListSubmissionResult(
    val success: Int? = null,
    val error: Int? = null,
    val submission: List<Submission>? = null
)
