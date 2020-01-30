package com.example.androidarchitecturesample.data.api

import com.example.androidarchitecturesample.data.model.Entry
import com.example.androidarchitecturesample.data.model.Promoted
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WykopApi {

    @GET("links/promoted/appkey/{appkey}/page/{page}")
    suspend fun fetchPromoted(
        @Path("page") page: Int,
        @Path("appkey") appkey: String = "gnr9daRtLW"
    ): Response<List<Promoted>>

    @GET("stream/entries/appkey/{appkey}/page/{page}")
    suspend fun fetchEntries(
        @Path("page") page: Int,
        @Path("appkey") appkey: String = "gnr9daRtLW"
    ): Response<List<Entry>>
}