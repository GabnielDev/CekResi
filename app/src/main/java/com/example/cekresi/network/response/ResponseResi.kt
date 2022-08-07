package com.example.cekresi.network.response

data class ResponseResi(
	val data: Data? = null,
	val message: String? = null,
	val status: Int? = null
)

data class Data(
	val summary: Summary? = null,
	val detail: Detail? = null,
	val history: ArrayList<HistoryItem>? = null
)

data class Summary(
	val date: String? = null,
	val amount: String? = null,
	val courier: String? = null,
	val service: String? = null,
	val weight: String? = null,
	val awb: String? = null,
	val status: String? = null,
	val desc: String? = null
)

data class Detail(
	val shipper: String? = null,
	val receiver: String? = null,
	val origin: String? = null,
	val destination: String? = null
)

data class HistoryItem(
	val date: String? = null,
	val location: String? = null,
	val desc: String? = null
)


