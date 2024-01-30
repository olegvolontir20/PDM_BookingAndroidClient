package com.booking.app.api

import com.booking.app.api.endpoints.ApartmentEndpoint
import com.booking.app.api.endpoints.UserEndpoint
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://bookingappapi20240120200613.azurewebsites.net/"

    val apartmentEndpoint: ApartmentEndpoint by lazy {
        retrofit.create(ApartmentEndpoint::class.java)
    }
    val userEndpoint: UserEndpoint by lazy{
        retrofit.create(UserEndpoint::class.java)
    }

    private val retrofit: Retrofit by lazy {
        val client = OkHttpClient.Builder()
            .cookieJar(MyCookieJar())
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}