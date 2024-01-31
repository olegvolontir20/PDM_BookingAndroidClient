package com.booking.app.api.endpoints

import com.booking.app.api.models.ApartmentModel
import com.booking.app.api.models.AddApartmentBookingModel
import com.booking.app.api.models.AddRoomBookingModel
import com.booking.app.api.models.ApartmentBookingModel
import com.booking.app.api.models.PaginatedResponse
import com.booking.app.api.models.RoomBookingModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BookingEndpoint {
    @POST("api/Booking/book-apartment")
    fun bookApartment(@Body model: AddApartmentBookingModel): Call<ApartmentBookingModel>

    @POST("api/Booking/book-room")
    fun bookRoom(@Body model: AddRoomBookingModel): Call<RoomBookingModel>

    @DELETE("api/Booking/room-book")
    fun deleteRoomBooking(@Query("id") id: Int): Call<Void>

    @DELETE("api/Booking/apartment-book")
    fun deleteApartmentBooking(@Query("id") id: Int): Call<Void>

    @GET("api/Booking/apartment-bookings")
    fun getApartmentBookings(): Call<List<ApartmentBookingModel>>

    @GET("api/Booking/room-bookings")
    fun getRoomBookings(): Call<List<RoomBookingModel>>
}