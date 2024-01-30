package com.booking.app.api.models

import java.util.Date

data class ApartmentBooking (

    var id: Int? = null,
    var userId: Int? = null,
    var apId: Int? = null,
    var firstDay: Date? = null,
    var lastDay: Date? = null,
    var numberOfPeople: Int? = null,
    var user: User? = null,
    var apartment: Apartment? = null
)