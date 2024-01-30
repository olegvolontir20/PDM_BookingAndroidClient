package com.booking.app.api.models

data class AddHotelModel(
    var name: String? = "",
    var city: String? = "",
    var country: String? = "",
    var phone: String? = "",
    var description: String? = "",
    var rooms : List<RoomModel>? = null,
    var pathImage: String? = ""
)
