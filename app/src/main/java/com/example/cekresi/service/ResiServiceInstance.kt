package com.example.cekresi.service

import com.example.cekresi.network.response.ResponseResi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ResiServiceInstance {

    @GET("track")
    suspend fun getResi(
        @Query("api_key") api_key: String?,
        @Query("courier") courier: String?,
        @Query("awb") awb: String?
    ): Response<ResponseResi>

}