package com.cvargas.myevents.data.model

import java.text.SimpleDateFormat
import java.util.*

data class Event(
    val id: String,
    val title: String,
    val description: String,
    val date: Date,
    val price: Double,
    val image: String,
    val longitude: Double,
    val latitude: Double
) {
    val createdDate: String
        get() = date.let { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date) }
}