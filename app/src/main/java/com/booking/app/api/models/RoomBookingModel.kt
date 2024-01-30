package com.booking.app.api.models

import java.util.Date

data class RoomBookingModel(
    var id: Int? = 0,
    var user_Id: Int? = 0,
    var room_Id: Int? = 0,
    var firstDay: Date? = null,
    var lastDay: Date? = null,
    var numberOfPeople: Int? = null,
    var apartment: ApartmentModel? = null
)
