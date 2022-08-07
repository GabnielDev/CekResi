package com.example.cekresi.repository

import com.example.cekresi.base.BaseApiResponse
import com.example.cekresi.base.NetworkResult
import com.example.cekresi.network.response.ResponseResi
import com.example.cekresi.remote.ResiDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResiRepository @Inject constructor(
    private val dataSource: ResiDataSource
) : BaseApiResponse() {

    suspend fun getResi(api_key: String?, courier: String?, awb: String?
    ): Flow<NetworkResult<ResponseResi>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall {
                dataSource.getResi(api_key, courier, awb)
            })
        }.flowOn(Dispatchers.IO)
    }

}