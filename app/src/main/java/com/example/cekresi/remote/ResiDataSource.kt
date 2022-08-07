package com.example.cekresi.remote

import com.example.cekresi.service.ResiServiceInstance
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResiDataSource @Inject constructor(
    private val service: ResiServiceInstance
) {

    suspend fun getResi(
        api_key: String?,
        courier: String?,
        awb: String?
    ) = service.getResi(api_key, courier, awb)


}