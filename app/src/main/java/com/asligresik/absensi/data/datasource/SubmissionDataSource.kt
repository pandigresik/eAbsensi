package com.asligresik.absensi.data.datasource

import com.asligresik.absensi.data.Result
import com.asligresik.absensi.data.model.Submission
import com.asligresik.absensi.data.response.SubmissionResponse
import com.asligresik.absensi.data.service.MainApi
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class SubmissionDataSource(private val submissionService: MainApi) {
    suspend fun create(startDate: String, endDate: String, description: String, type: String): Result<Submission> {
        val postCreate = submissionService.create(startDate, endDate, description, type)
        try {
            val response = postCreate.await()
            if (response.isSuccessful) {
                return Result.Success(response.body()?.data!!)
            }
            return Result.Error(IOException("Error occurred during fetching movies!"))
        } catch (e: Exception) {
            return Result.Error(IOException("Error occurred during fetching movies!"))
        }
    }

    suspend fun loadData(status: String, type: String): Result<List<Submission>> {
        val getData =
            when(status){
                "Active" -> submissionService.submissionActive(type)
                "Approval" -> submissionService.submissionApproval(type)
                else -> submissionService.submission(type)
            }
        try {
            val response = getData.await()
            if (response.isSuccessful) {
                return Result.Success(response.body()?.data!!)
            }
            return Result.Error(IOException("Error occurred during fetching movies!"))
        } catch (e: Exception) {
            return Result.Error(IOException("Error occurred during fetching movies!"))
        }
    }
}