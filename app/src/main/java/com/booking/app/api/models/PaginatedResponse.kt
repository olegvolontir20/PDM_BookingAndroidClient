package com.booking.app.api.models

data class PaginatedResponse<T>(
    var count: Int = 0,
    var perPage: Int = 0,
    var currentPage: Int = 0,
    var items: List<T> = emptyList()
)
