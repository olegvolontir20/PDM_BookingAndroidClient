package com.booking.app.api.endpoints

import com.booking.app.api.models.AddApartmentModel
import com.booking.app.api.models.AddHotelModel
import com.booking.app.api.models.ApartmentModel
import com.booking.app.api.models.BookModel
import com.booking.app.api.models.HotelModel
import com.booking.app.api.models.PaginatedResponse
import com.booking.app.api.models.RoomModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface HotelEndpoint {
    @GET("api/Hotel")
    fun getHotels(): Call<PaginatedResponse<HotelModel>>

    @POST("api/Hotel")
    fun postHotel(@Body hotel: AddHotelModel): Call<HotelModel>

    @GET("api/Hotel")
    fun getHotel(@Query("id") id: Int): Call<ApartmentModel>

    @GET("api/Hotel")
    fun getRoom(@Query("id") id: Int): Call<RoomModel>

    @DELETE("api/Hotel")
    fun deleteHotel(@Query("id") id: Int): Call<Void>

    @GET("api/Hotel/search")
    fun searchHotels(@Url url: String, @Query("model") model: BookModel): Call<List<HotelModel>>

    @GET("api/Hotel/last-three")
    fun getLastThreeHotels(): Call<List<HotelModel>>
}