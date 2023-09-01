package com.example.healthapp

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Calories(
    @Json(name = "name") val name: String,
    @Json(name = "calories_per_hour") val calories_per_hour : Int,
    @Json(name = "duration_minutes") val duration_minutes: Int,
    @Json(name = "total_calories") val total_calories: Int
)
