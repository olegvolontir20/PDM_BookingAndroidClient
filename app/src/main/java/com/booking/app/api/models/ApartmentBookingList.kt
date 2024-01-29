package com.booking.app.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ApartmentBookingList {

    @SerializedName("count")
    @Expose
    var count: Int? = null

    @SerializedName("perPage")
    @Expose
    var perPage: Int? = null

    @SerializedName("currentPage")
    @Expose
    var currentPage: Int? = null

    @SerializedName("apartment")
    @Expose
    var apartment: Apartment? = null

    @SerializedName("apartment")
    @Expose
    var bookModel: BookModel? = null

}