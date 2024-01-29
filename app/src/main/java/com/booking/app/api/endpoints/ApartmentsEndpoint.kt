package com.booking.app.api.endpoints

import com.booking.app.api.models.Apartment
import com.booking.app.api.models.ApartmentResponseList
import com.booking.app.api.models.BookModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApartmentsEndpoint {
    @GET("api/Apartments")
    fun getApartments(): Call<ApartmentResponseList>

    @GET("api/search")
    fun searchFilterAndSortApartaments(@Body bookModel: BookModel): Call<ApartmentResponseList>

    @GET("api/last-three")
    fun getLastThreeLocations(): Call<ApartmentResponseList>

    @POST("api/create")
    fun addApartment(@Body apartment: Apartment): Call<Apartment>

}