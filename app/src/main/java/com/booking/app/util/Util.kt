package com.booking.app.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayInputStream

fun imageFromBase64(
    base64String: String
): Bitmap?
{
    try {
        val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
        val inputStream = ByteArrayInputStream(decodedBytes)
        return BitmapFactory.decodeStream(inputStream)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}