package com.asligresik.absensi.data.service

import com.asligresik.absensi.AppConstants

object ApiFactory{
    val loginApi : LoginApi = RetrofitFactory.retrofit(AppConstants.JSON_PLACEHOLDER_BASE_URL)
        .create(LoginApi::class.java)

    fun createMainRetrofit(authToken: String) = RetrofitFactory.retrofit(AppConstants.JSON_PLACEHOLDER_BASE_URL,authToken).create(MainApi::class.java)

}