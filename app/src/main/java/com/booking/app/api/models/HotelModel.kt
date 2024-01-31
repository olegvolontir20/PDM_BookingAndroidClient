package com.booking.app.api.models

data class HotelModel(
    var id: Int = 0,
    var name: String = "",
    var city: String = "",
    var country: String = "",
    var phone: String = "",
    var description: String = "",
    var rooms: List<RoomModel> = emptyList(),
    var pathImage: String = ""
)
