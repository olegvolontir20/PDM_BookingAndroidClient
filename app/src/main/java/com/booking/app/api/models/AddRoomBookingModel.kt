package com.booking.app.api.models

import java.util.Date

data class AddRoomBookingModel(
    var room_Id: Int? = null,
    var firstDay: Date? = null,
    var lastDay: Date? = null,
    var numberOfPeople: Int? = null,
)
