package com.booking.app.api.models
data class ApartmentModel (
    var id: Int? = null,
    var name: String? = null,
    var city: String? = null,
    var country: String? = null,
    var phone: String? = null,
    var capacity: Int? = null,
    var price: Int? = null,
    var description: String? = null,
    var pathImage: String? = null
)