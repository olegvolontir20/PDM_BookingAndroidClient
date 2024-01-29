package com.booking.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.booking.app.api.RetrofitInitializer
import com.booking.app.ui.theme.BookingAppTheme

import android.widget.Toast
import com.booking.app.api.models.ApartmentListReponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val call: Call<ApartmentListReponse> = RetrofitInitializer.bookingApartmentsEndpoint.getApartments()

        call.enqueue(object : Callback<ApartmentListReponse> {
            override fun onResponse(call: Call<ApartmentListReponse>, response: Response<ApartmentListReponse>) {
                if (response.isSuccessful) {
                    val data: ApartmentListReponse? = response.body()
                    val mess = data?.apartmentList?.get(0)?.description.toString()
                    showToast("Apartment1 descr: " + mess)
                } else {
                    showToast("onResponse failed")
                }
            }

            override fun onFailure(call: Call<ApartmentListReponse>, t: Throwable) {
                showToast(t.message.toString())
            }
        })

        setContent {
            BookingAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BookingAppTheme {
        Greeting("Android")
    }
}