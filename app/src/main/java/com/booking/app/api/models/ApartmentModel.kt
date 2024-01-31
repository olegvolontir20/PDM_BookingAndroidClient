package com.booking.app.api.models
data class ApartmentModel (
    var id: Int = 0,
    var name: String = "",
    var city: String = "",
    var country: String = "",
    var phone: String = "",
    var capacity: Int = 0,
    var price: Int = 0,
    var description: String = "",
    var pathImage: String = ""
)