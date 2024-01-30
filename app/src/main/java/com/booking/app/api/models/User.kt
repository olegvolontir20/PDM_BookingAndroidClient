package com.booking.app.api.models

data class User (
    var id: Int? = null,
    var name: String? = null,
    var password: String? = null,
    var email: String? = null,
    var phoneNumber: String? = null,
    //var favHotelList: List<Hotel>? = null,
    var favApartmentList: List<Apartment>? = null
)