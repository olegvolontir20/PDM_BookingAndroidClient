package com.booking.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.booking.app.ui.theme.BookingAppTheme

class ApartmentBooking : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_apartment_booking)

        setContent() {
            BookingAppTheme {

            }
        }
    }
}

