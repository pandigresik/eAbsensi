package com.asligresik.absensi.data.service

import com.asligresik.absensi.data.response.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApi{
    @POST("auth/login")
    @FormUrlEncoded()
    fun login(@Field("username") username: String, @Field("password") password: String) : Deferred<Response<LoginResponse>>
}