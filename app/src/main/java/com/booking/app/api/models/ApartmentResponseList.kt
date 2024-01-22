package com.booking.app.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ApartmentResponseList {
    @SerializedName("count")
    @Expose
    var count: Int? = null

    @SerializedName("perPage")
    @Expose
    var perPage: Int? = null

    @SerializedName("currentPage")
    @Expose
    var currentPage: Int? = null

    @SerializedName("items")
    @Expose
    var apartmentList: ApartmentList? = null
}