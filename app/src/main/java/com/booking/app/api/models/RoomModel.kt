package com.booking.app.api.models

data class RoomModel(
    var id: Int? = null,
    var numberOfRoom: Int? = null,
    var capacity: Int? = null,
    var price: Int? = null,
    var hotel: HotelModel? = null
)
