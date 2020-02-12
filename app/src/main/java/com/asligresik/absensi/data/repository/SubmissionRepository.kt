package com.asligresik.absensi.data.repository

import android.util.Log
import com.asligresik.absensi.data.Result
import com.asligresik.absensi.data.datasource.SubmissionDataSource
import com.asligresik.absensi.data.model.Submission

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class SubmissionRepository(val dataSource: SubmissionDataSource){
    suspend fun create(startDate: String, endDate: String, description: String, type: String): Result<Submission> {
        // handle login
        val result = dataSource.create(startDate, endDate, description,type)
        if (result is Result.Success) {

        }
        return result
    }

    suspend fun loadData(status: String, type: String): Result<List<Submission>> {
        val result = dataSource.loadData(status, type)
        //Log.e("loadData submission", result.toString())
        if (result is Result.Success) {

        }
        return result
    }
}
