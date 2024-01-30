package com.booking.app.api.models

data class PaginatedResponse<T>(
    var count: Int? = null,
    var perPage: Int? = null,
    var currentPage: Int? = null,
    var items: List<T>? = null
)
