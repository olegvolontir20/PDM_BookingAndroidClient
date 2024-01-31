package com.booking.app.api.models

data class RoomModel(
    var id: Int = 0,
    var numberOfRoom: Int = 0,
    var capacity: Int = 0,
    var price: Int = 0,
    var hotel: HotelModel = HotelModel(),
    var isSelected: Boolean = false,
)
