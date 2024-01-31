package com.booking.app

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.NumberPicker
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.booking.app.api.endpoints.BookingEndpoint
import com.booking.app.api.endpoints.HotelEndpoint
import com.booking.app.api.models.AddRoomBookingModel
import com.booking.app.api.models.ApartmentModel
import com.booking.app.api.models.HotelModel
import com.booking.app.api.models.RoomModel
import com.booking.app.ui.theme.BookingAppTheme
import com.booking.app.util.imageFromBase64
import java.util.Calendar
import java.util.Date

class HotelScreenActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val hotel = intent.getParcelableExtra<HotelModel>("hotel") ?: HotelModel()

        setContent {
            BookingAppTheme {
                HotelDetailsScreen(hotel)
            }
        }
    }
}
var selectedRoom: RoomModel? = null
var firstDayCalendar = Calendar.getInstance()
var lastDayCalendar = Calendar.getInstance()
var selectedNumberOfPeople: Int = 1
class BookingService(private val bookingEndpoint: BookingEndpoint) {

    suspend fun addRoomBooking(addRoomBookingModel: AddRoomBookingModel): Boolean {
        val response = bookingEndpoint.bookRoom(addRoomBookingModel)
        return response.isExecuted
    }
}

fun openDatePickerDialog(calendar: Calendar, onDateSelected: (Date) -> Unit) {
    val datePickerDialog = DatePickerDialog(
        this,
        {_ , year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            onDateSelected(calendar.time)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    datePickerDialog.show()
}

fun openNumberPickerDialog(onNumberSelected: (Int) -> Unit) {
    val numberPicker = NumberPicker(this)
    numberPicker.minValue = 1
    numberPicker.maxValue = 5
    numberPicker.value = selectedNumberOfPeople

    AlertDialog.Builder(this)
        .setTitle("Selectați numărul de persoane")
        .setView(numberPicker)
        .setPositiveButton("OK") {_ , _ ->
            onNumberSelected(numberPicker.value)
        }
        .setNegativeButton("Anulare") { dialog, _ ->
            dialog.dismiss()
        }
        .show()
}
@Composable
fun performReservation(selectedRoom: RoomModel) {
    var addRoomBookingModel = AddRoomBookingModel(
        room_Id = selectedRoom.id ?: 0,
        firstDay = firstDayCalendar.time,
        lastDay = lastDayCalendar.time,
        numberOfPeople = selectedNumberOfPeople
    )

    openDatePickerDialog(firstDayCalendar) { selectedDate ->
        firstDayCalendar.time = selectedDate
    }

    openDatePickerDialog(lastDayCalendar) { selectedDate ->
        lastDayCalendar.time = selectedDate
    }

    openNumberPickerDialog { selectedNumber ->
        selectedNumberOfPeople = selectedNumber
    }
}

@Composable
fun RoomItem(room: RoomModel, onItemSeleted: (Int, Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Camera ${room.numberOfRoom}")
        Text(text = "Preț: ${room.price}")
        Checkbox(
            checked = room.isSelected,
            onCheckedChange = { isChecked ->
                onItemSeleted(room.numberOfRoom, isChecked)
            }
        )
    }
}
@Composable
fun HotelDetailsScreen(hotel: HotelModel) {
    Surface {
        var bitmap: Bitmap =
            if (imageFromBase64(hotel.pathImage) != null) {
                imageFromBase64(hotel.pathImage) as Bitmap
            } else {
                Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
            }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Hotel",
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .width(100.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop
            )

            Text(
                text = hotel.name,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                text = "${hotel.city}, ${hotel.country}",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 4.dp)
            )

            Text(
                text = hotel.description,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 4.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                itemsIndexed(hotel.rooms) { index, room ->
                    RoomItem(room = room, onItemSeleted = { roomNumber, isSelected ->
                        hotel.rooms.find { it.numberOfRoom == roomNumber }?.isSelected = isSelected
                    })
                }
            }

            Button(
                onClick = {
                    val selectedRoom = hotel.rooms.find { it.isSelected }
                    if (selectedRoom != null) {
                        performReservation(selectedRoom)
                    } else {

                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text(text = "Rezervă")
            }
        }
    }
}





