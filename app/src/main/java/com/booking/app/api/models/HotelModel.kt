package com.booking.app.api.models

data class HotelModel(
    var id: Int? = null,
    var name: String? = null,
    var city: String? = null,
    var country: String? = null,
    var phone: String? = null,
    var description: String? = null,
    var rooms: List<RoomModel>? = null,
    var pathImage: String? = null
)
