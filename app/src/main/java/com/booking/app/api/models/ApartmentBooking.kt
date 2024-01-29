package com.booking.app.api.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.util.Date


class ApartmentBooking {
    @SerializedName("id")
    @Expose
    private val id = 0

    @SerializedName("user_id")
    @Expose
    private val userId = 0

    @SerializedName("ap_id")
    @Expose
    private val apId = 0

    @SerializedName("first_day")
    @Expose
    private val firstDay: Date? = null

    @SerializedName("last_day")
    @Expose
    private val lastDay: Date? = null

    @SerializedName("number_of_people")
    @Expose
    private val numberOfPeople = 0

    @SerializedName("user")
    @Expose
    private val user: User? = null

    @SerializedName("apartment")
    @Expose
    private val apartment: Apartment? = null
}