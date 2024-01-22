package com.booking.app.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ApartmentList {
    @SerializedName("count")
    @Expose
    var count: Int? = null

    @SerializedName("apartments")
    @Expose
    var apartments: List<Apartment>? = null
}