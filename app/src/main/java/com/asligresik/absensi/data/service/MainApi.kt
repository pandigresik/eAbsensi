package com.asligresik.absensi.data.service

import com.asligresik.absensi.data.model.ApprovalSubmission
import com.asligresik.absensi.data.response.ListSubmissionResponse
import com.asligresik.absensi.data.response.SubmissionResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface MainApi{

    @POST("submission")
    @FormUrlEncoded()
    fun create(@Field("start_date") startDate: String, @Field("end_date") endDate: String, @Field("description") description: String, @Field("type") type: String) : Deferred<Response<SubmissionResponse>>
    @GET("submission/active/{type}")
    fun submissionActive(@Path("type") type: String) : Deferred<Response<ListSubmissionResponse>>
    @GET("submission/history/{type}")
    fun submission(@Path("type") type: String) : Deferred<Response<ListSubmissionResponse>>
    @GET("submission/approval/{type}")
    fun submissionApproval(@Path("type") type: String) : Deferred<Response<ListSubmissionResponse>>
    @GET("submission/approvalHistory/{type}")
    fun submissionApprovalHistory(@Path("type") type: String) : Deferred<Response<ListSubmissionResponse>>

    @PATCH("submission/{id}")
    fun approveRejectSubmission(@Path("id") id: Int, @Body dataApproval: ApprovalSubmission) : Deferred<Response<SubmissionResponse>>
/*
    @GET("/photos")
    fun getPhotos() : Deferred<Response<List<PlaceholderPhotos>>>
*/
}