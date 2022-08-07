package com.example.cekresi.helper

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun convertDate(
    date: String?,
    format: String = "dd-MM-yyyy HH:mm"
): String {
    var formattedDate = ""
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone("UTC")

    try {
        val parseDate = sdf.parse(date)
        formattedDate = SimpleDateFormat(format).format(parseDate)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return formattedDate
}