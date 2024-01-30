package com.booking.app

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.booking.app.api.RetrofitClient
import com.booking.app.api.models.ApartmentModel
import com.booking.app.api.models.HotelModel
import com.booking.app.api.models.PaginatedResponse
import com.booking.app.ui.theme.BookingAppTheme
import kotlinx.coroutines.CoroutineStart
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.io.encoding.Base64

class HomeScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BookingAppTheme {
                HomeScreenTabs()
            }
        }
    }
}

fun getApartmentList(
    context: Context,
    onReceive: (List<ApartmentModel>?) -> Unit
)
{
    val call: Call<PaginatedResponse<ApartmentModel>> = RetrofitClient.apartmentEndpoint.getApartments()

    call.enqueue(object : Callback<PaginatedResponse<ApartmentModel>> {
        override fun onResponse(
            call: Call<PaginatedResponse<ApartmentModel>>,
            response: Response<PaginatedResponse<ApartmentModel>>
        ) {
            if (response.isSuccessful) {
                val data: PaginatedResponse<ApartmentModel>? = response.body()
                val mess = data?.items?.get(0)?.description.toString()
                showToast("Apartment1 descr: " + mess, context)
                onReceive.invoke(data?.items)
            } else {
                showToast("onResponse failed", context)

            }
        }

        override fun onFailure(call: Call<PaginatedResponse<ApartmentModel>>, t: Throwable) {
            showToast(t.message.toString(), context)
        }
    })
}

fun getHotelList(
    context: Context,
    onReceive: (List<HotelModel>?) -> Unit
)
{
    val call: Call<PaginatedResponse<HotelModel>> = RetrofitClient.hotelEndpoint.getHotels()

    call.enqueue(object : Callback<PaginatedResponse<HotelModel>> {
        override fun onResponse(
            call: Call<PaginatedResponse<HotelModel>>,
            response: Response<PaginatedResponse<HotelModel>>
        ) {
            if (response.isSuccessful) {
                val data: PaginatedResponse<HotelModel>? = response.body()
                val mess = data?.items?.get(0)?.description.toString()
                showToast("Hotel1 descr: " + mess, context)
                onReceive.invoke(data?.items)
            } else {
                showToast("onResponse failed", context)
            }
        }

        override fun onFailure(call: Call<PaginatedResponse<HotelModel>>, t: Throwable) {
            showToast(t.message.toString(), context)
        }
    })
}

@Preview
@Composable
fun HomeScreenTabs(){
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column {
        // Tab Row
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary),
            contentColor = Color.White,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
                        .height(2.dp)
                        .background(Color.White)
                )
            }
        ) {
            Tab(
                selected = selectedTabIndex == 0,
                onClick = { selectedTabIndex = 0 },
                icon = { Icon(Icons.Default.Home, contentDescription = "Apartments") },
                text = { Text("Apartments") }
            )
            Tab(
                selected = selectedTabIndex == 1,
                onClick = { selectedTabIndex = 1 },
                icon = { Icon(Icons.Default.Home, contentDescription = "Hotels") },
                text = { Text("Hotels") }
            )
            Tab(
                selected = selectedTabIndex == 2,
                onClick = { selectedTabIndex = 2 },
                icon = { Icon(Icons.Default.Person, contentDescription = "Bookings") },
                text = { Text("Bookings") }
            )
        }

        // Content area based on the selected tab
        when (selectedTabIndex) {
            0 -> ApartmentsTabContent()
            1 -> HotelsTabContent()
            2 -> BookingsTabContent()
        }
    }
}

@Composable
fun ApartmentsTabContent() {
    Surface {
        var items: List<ApartmentModel> by remember {
            mutableStateOf(emptyList())
        }

        val updateItems: (List<ApartmentModel>?) -> Unit = {

        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Apartments")
        }

    }
}

//@Composable
//fun ApartmentList(
//    hotels: List<ApartmentModel>
//)
//{
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxSize()
//    )
//    {
//        items(hotels){
//            hotel ->
//
//        }
//    }
//}
//
//@Composable
//fun ApartmentlListItem(
//    apartmentModel: ApartmentModel,
//    context: Context
//) {
//    ListItem(
//        Icon(
//            bitmap =  apartmentModel.,
//            contentDescription = )
//    )
//}
//
//fun decodeBase64ToBitmap(base64String: String): Bitmap? {
//    val decodedBytes = Base64.decode(base64String, CoroutineStart.DEFAULT)
//    return BitmapFactory.decodeStream(ByteArrayInputStream(decodedBytes))
//}

@Composable
fun HotelsTabContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    }
}

@Composable
fun BookingsTabContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bookings")
    }
}