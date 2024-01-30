package com.booking.app.api.endpoints

import com.booking.app.api.models.AddApartmentModel
import com.booking.app.api.models.ApartmentModel
import com.booking.app.api.models.BookModel
import com.booking.app.api.models.PaginatedResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface ApartmentEndpoint {
    @GET("api/Apartment")
    fun getApartments(): Call<PaginatedResponse<ApartmentModel>>

    @POST("api/Apartment")
    fun postApartment(@Body apartment: AddApartmentModel): Call<ApartmentModel>

    @GET("api/Apartment")
    fun getApartment(@Query("id") id: Int): Call<ApartmentModel>

//    @PUT("api/Apartment")
//    fun putApartment(@Query("id") apartmentId: Int, @Body apartment: Apartment): Call<Apartment>

    @DELETE("api/Apartment")
    fun deleteApartment(@Query("id") id: Int): Call<Void>

    @GET("api/Apartment/search")
    fun searchApartments(@Url url: String, @Query("model") model: BookModel): Call<List<ApartmentModel>>

    @GET("api/Apartment/last-three")
    fun getLastThreeApartments(): Call<List<ApartmentModel>>
}