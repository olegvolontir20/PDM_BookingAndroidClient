package com.booking.app.api.endpoints

import com.booking.app.api.models.Apartment
import com.booking.app.api.models.PaginatedResponse
import retrofit2.Call
import retrofit2.http.GET

interface BookingApartmentsEndpoint {
    @GET("api/Apartment")
    fun getApartments(): Call<PaginatedResponse<Apartment>>
}