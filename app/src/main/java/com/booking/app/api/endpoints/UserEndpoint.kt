package com.booking.app.api.endpoints

import com.booking.app.api.models.LoginModel
import com.booking.app.api.models.RegisterModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserEndpoint {
    @POST("api/User/Register")
    fun registerUser(@Body registerModel: RegisterModel): Call<Void>

    @POST("api/User/Login")
    fun loginUser(@Body loginModel: LoginModel): Call<Void>
}