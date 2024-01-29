package com.booking.app.api.endpoints

import com.booking.app.api.models.ApartmentListReponse
import retrofit2.Call
import retrofit2.http.GET

interface BookingApartmentsEndpoint {
    @GET("api/Apartment")
    fun getApartments(): Call<ApartmentListReponse>
}