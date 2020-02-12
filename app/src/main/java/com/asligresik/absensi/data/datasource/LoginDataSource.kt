package com.asligresik.absensi.data.datasource

import com.asligresik.absensi.data.Result
import com.asligresik.absensi.data.model.DataLogin
import com.asligresik.absensi.data.service.ApiFactory
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {
    private val loginService = ApiFactory.loginApi
    suspend fun login(username: String, password: String): Result<DataLogin> {
        // TODO: handle loggedInUser authentication
        val postLogin = loginService.login(username, password)
        try {
            val response = postLogin.await()
            if (response.isSuccessful) {
                return Result.Success(response.body()?.data!!)
            }
            return Result.Error(IOException("Error occurred during fetching movies!"))
        } catch (e: Exception) {
            return Result.Error(IOException("Error occurred during fetching movies!"))
        }
    }
}