package com.example.cekresi.network.request

import retrofit2.http.Query

data class ResiRequest(
    var api_key: String = "d8f989492915d244cca1f127588d53b8a622a235ee85d24959efac5168765ada",
    var courier: String,
    var awb: String
)