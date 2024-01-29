package com.booking.app.api

import com.booking.app.api.endpoints.ApartmentsEndpoint
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInitializer {
    private const val BASE_URL = "https://bookingappapi20240120200613.azurewebsites.net/"

    private val retrofit: Retrofit by lazy {
        val client = OkHttpClient.Builder()
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val bookingApartmentsEndpoint: ApartmentsEndpoint by lazy {
        retrofit.create(ApartmentsEndpoint::class.java)
    }
}