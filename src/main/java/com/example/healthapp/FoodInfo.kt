package com.example.healthapp

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoodInfo(@Json(name = "name") val name: String,
    @Json(name = "calories") val calories: Float,
    @Json(name = "serving_size_g") val serving_size_g: Float,
    @Json(name = "fat_total_g") val fat_total_g: Float,
    @Json(name = "fat_saturated_g") val fat_saturated_g: Float,
    @Json(name = "protein_g") val protein_g: Float,
    @Json(name = "sodium_mg") val sodium_mg: Float,
    @Json(name = "potassium_mg") val potassium_mg: Float,
    @Json(name = "cholesterol_mg") val cholesterol_mg: Float,
    @Json(name = "carbohydrates_total_g") val carbohydrates_total_g: Float,
    @Json(name = "fiber_g") val fiber_g: Float,
    @Json(name = "sugar_g") val sugar_g: Float
)