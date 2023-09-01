package com.example.healthapp

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

fun getTotalCalories(name: String, weight: Int = 150, duration_minutes: Int = 60, callback : (List<Calories>) -> Unit){
    val client = OkHttpClient()
    val request = Request.Builder().url("https://api.api-ninjas.com/v1/caloriesburned?activity=$name&weight=$weight&duration=$duration_minutes")
        .get().addHeader("X-Api-Key", "f+vVZUBuTuue5oHVGzle2g==4kevgXLAFtZFfH1o").build()

    client.newCall(request).enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            response.body?.use { body ->
                val moshiInstance: Moshi = Moshi.Builder().build()
                val caloriesListAdapter: JsonAdapter<List<Calories>> =
                    moshiInstance.adapter(Types.newParameterizedType(List::class.java, Calories::class.java))

                val totalCaloriesList: List<Calories>? = caloriesListAdapter.fromJson(body.source())

                if (totalCaloriesList != null) {
                    callback(totalCaloriesList)
                } else {
                    // Handle the case where JSON parsing failed
                }
            }
        }
    })


}
fun getFoodInfo(name: String, callback: (List<FoodInfo>) -> Unit){
    val client = OkHttpClient()
    val request = Request.Builder().url("https://api.api-ninjas.com/v1/nutrition?query=$name")
        .get().addHeader("X-Api-Key", "f+vVZUBuTuue5oHVGzle2g==4kevgXLAFtZFfH1o").build()

    client.newCall(request).enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            response.body?.use { body ->
                val moshiInstance: Moshi = Moshi.Builder().build()
                val foodInfoListAdapter: JsonAdapter<List<FoodInfo>> =
                    moshiInstance.adapter(Types.newParameterizedType(List::class.java, FoodInfo::class.java))

                val foodInfoList: List<FoodInfo>? = foodInfoListAdapter.fromJson(body.source())

                if (foodInfoList != null) {
                    callback(foodInfoList)
                } else {
                    // Handle the case where JSON parsing failed
                }
            }
        }
    })

}