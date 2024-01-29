package com.booking.app.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Apartment {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("city")
    @Expose
    var city: String? = null

    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("phone")
    @Expose
    var phone: String? = null

    @SerializedName("capacity")
    @Expose
    var capacity: Int? = null

    @SerializedName("price")
    @Expose
    var price: Int? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("pathImage")
    @Expose
    var pathImage: String? = null
}