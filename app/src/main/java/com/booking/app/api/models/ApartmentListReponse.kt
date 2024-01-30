package com.booking.app.api.models
data class ApartmentListReponse (
    var count: Int? = null,
    var perPage: Int? = null,
    var currentPage: Int? = null,
    var apartmentList: List<Apartment>? = null
)