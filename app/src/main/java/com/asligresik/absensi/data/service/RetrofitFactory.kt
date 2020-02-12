package com.asligresik.absensi.data.service

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory{

    class AuthInterceptor(val authToken: String):Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val newUrl = chain.request().url()
                .newBuilder()
                .build()

            val newRequest = chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer $authToken")
                .url(newUrl)
                .build()

            return chain.proceed(newRequest)
        }
    }

    private val loggingInterceptor =  HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    fun createClient(authToken: String? = null): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient().newBuilder()
        if(!authToken.isNullOrEmpty()){
            okHttpClientBuilder.addInterceptor(AuthInterceptor(authToken))
        }
        return okHttpClientBuilder.addInterceptor(loggingInterceptor)
            .build()
    }

    fun retrofit(baseUrl : String, authToken: String? = null) : Retrofit {
        val client = createClient(authToken)
        return Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
}