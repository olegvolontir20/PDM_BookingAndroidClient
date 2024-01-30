package com.booking.app.api.models

import java.util.Date

data class AddApartmentBookingModel (
    var ap_Id: Int? = null,
    var firstDay: Date? = null,
    var lastDay: Date? = null,
    var numberOfPeople: Int? = null,
)