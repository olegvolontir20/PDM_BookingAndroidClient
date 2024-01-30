package com.booking.app.api.endpoints

import com.booking.app.api.models.Apartment
import com.booking.app.api.models.BookModel
import com.booking.app.api.models.PaginatedResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApartmentEndpoint {
    @GET("api/Apartment")
    fun getApartments(): Call<PaginatedResponse<Apartment>>

    @GET("api/search")
    fun searchFilterAndSortApartments(@Body bookModel: BookModel): Call<PaginatedResponse<Apartment>>

    @GET("api/last-three")
    fun getLastThreeLocations(): Call<PaginatedResponse<Apartment>>

    @POST("api/create")
    fun addApartment(@Body apartment: Apartment): Call<Apartment>

}