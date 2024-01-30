package com.booking.app.api.models

data class RegisterModel(
    var userName: String,
    var password: String,
    var email: String,
    var phoneNumber: String
)