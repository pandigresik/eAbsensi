package com.asligresik.absensi.ui.submission

import com.asligresik.absensi.data.model.Submission

/**
 * Authentication result : success (user details) or error message.
 */
data class SubmissionResult(
    val success: Int? = null,
    val error: Int? = null,
    val submission: Submission? = null
)
