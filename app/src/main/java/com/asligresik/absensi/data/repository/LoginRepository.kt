package com.asligresik.absensi.data.repository

import com.asligresik.absensi.data.datasource.LoginDataSource
import com.asligresik.absensi.data.Result
import com.asligresik.absensi.data.model.DataLogin
import com.asligresik.absensi.data.model.LoggedInUser

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        //dataSource.logout()
    }

    suspend fun login(username: String, password: String): Result<DataLogin> {
        // handle login
        val result = dataSource.login(username, password)

        if (result is Result.Success) {
            //Log.d("loggedInUser",result.data.toString())
            setLoggedInUser(result.data.user!!)
        }

        return result
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}
