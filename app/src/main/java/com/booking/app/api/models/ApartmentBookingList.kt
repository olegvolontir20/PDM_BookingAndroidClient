package com.booking.app.api.models
data class ApartmentBookingList (
    var count: Int? = null,
    var perPage: Int? = null,
    var currentPage: Int? = null,
    var apartment: Apartment? = null,
    var bookModel: BookModel? = null

)