package com.booking.app.api.models

import java.io.Serializable

class BookModel : Serializable {
    var country: String? = null
    var city: String? = null
    var startDate: String? = null
    var endDate: String? = null
    var capacity: String? = null
}
