package com.booking.app

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.booking.app.api.models.ApartmentModel
import com.booking.app.ui.theme.BookingAppTheme

class ApartmentScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getApartment(this) { apartment ->
            setContent {
                BookingAppTheme {
                    ApartmentDetailsScreen(apartment)
                }
            }
        }
    }
}

fun getApartment(context: Context, onReceive: (ApartmentModel) -> Unit) {
    val apartment = ApartmentModel()
    onReceive(apartment)
}

//ApartmentDetailsScreenPreview e doar pentru afisare in preview din dreapta cand apesi "Split" sau "Design",
//ceasta functie nu ajunge in aplicatie in sine + are scoase variabilele deoarec Preview nu stie sa le arate vizual
@Preview
@Composable
fun ApartmentDetailsScreenPreview()
{
    Surface{
        Column(
            modifier = Modifier.
            padding(16.dp).
            fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(text = " ")
            Text(text = "Nume Apartament: ")
            Text(text = "Descriere: ")
            Text(text = "Preț: ")
            // Butonul de rezervare
            Button(onClick = { /* Logica de rezervare */ }) {
                Text(text = "Rezervă acum")
            }
        }
    }
}

@Composable
fun ApartmentDetailsScreen(apartment: ApartmentModel) {
    Surface {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(text = " ${apartment.pathImage}")
            Text(text = "Nume Apartament: ${apartment.name}")
            Text(text = "Descriere: ${apartment.description}")
            Text(text = "Preț: ${apartment.price}")
            // Butonul de rezervare
            Button(onClick = { /* Logica de rezervare */ }) {
                Text(text = "Rezervă acum")
            }
        }
    }
}

