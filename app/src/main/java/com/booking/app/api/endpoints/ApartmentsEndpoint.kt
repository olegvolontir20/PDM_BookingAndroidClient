package com.booking.app.api.endpoints

import com.booking.app.api.models.Apartment
import com.booking.app.api.models.ApartmentListReponse
import com.booking.app.api.models.BookModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApartmentsEndpoint {
    @GET("api/Apartments")
    fun getApartments(): Call<ApartmentListReponse>

    @GET("api/search")
    fun searchFilterAndSortApartaments(@Body bookModel: BookModel): Call<ApartmentListReponse>

    @GET("api/last-three")
    fun getLastThreeLocations(): Call<ApartmentListReponse>

    @POST("api/create")
    fun addApartment(@Body apartment: Apartment): Call<Apartment>

}