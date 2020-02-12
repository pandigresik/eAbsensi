package com.asligresik.absensi.ui.submission

/**
 * Data validation state of the login form.
 */
data class SubmissionFormState(
    val startDateError: Int? = null,
    val endDateError: Int? = null,
    val descriptionError: Int? = null,
    val isDataValid: Boolean = false
)
