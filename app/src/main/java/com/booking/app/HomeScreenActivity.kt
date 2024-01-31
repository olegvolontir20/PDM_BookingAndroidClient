package com.booking.app

import android.R.attr.maxLines
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import com.booking.app.api.RetrofitClient
import com.booking.app.api.models.ApartmentModel
import com.booking.app.api.models.HotelModel
import com.booking.app.api.models.PaginatedResponse
import com.booking.app.ui.theme.BookingAppTheme
import com.booking.app.util.imageFromBase64
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayInputStream


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
    onReceive: (List<ApartmentModel>) -> Unit
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
                onReceive.invoke(data!!.items)
            } else {

            }
        }

        override fun onFailure(call: Call<PaginatedResponse<ApartmentModel>>, t: Throwable) {
            showToast(t.message.toString(), context)
        }
    })
}

fun getHotelList(
    context: Context,
    onReceive: (List<HotelModel>) -> Unit
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

                onReceive.invoke(data!!.items)
            } else {

            }
        }

        override fun onFailure(call: Call<PaginatedResponse<HotelModel>>, t: Throwable) {
            showToast(t.message.toString(), context)
        }
    })
}

fun apartmentDetails(apartment: ApartmentModel, context: Context){
    context.startActivity(Intent(context, ApartmentScreenActivity::class.java).apply {
        putExtras(bundleOf("apartment" to apartment))
    })
}

@Preview
@Composable
fun HomeScreenPreview() {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
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
                onClick = {},
                icon = { Icon(Icons.Default.Home, contentDescription = "Apartments") },
                text = { Text("Apartments") }
            )
            Tab(
                selected = selectedTabIndex == 1,
                onClick = { },
                icon = { Icon(Icons.Default.Home, contentDescription = "Hotels") },
                text = { Text("Hotels") }
            )
            Tab(
                selected = selectedTabIndex == 2,
                onClick = { },
                icon = { Icon(Icons.Default.Person, contentDescription = "Bookings") },
                text = { Text("Bookings") }
            )
        }

        when (selectedTabIndex) {
            0 -> Text(text = "Tab")
            1 -> Text(text = "Tab")
            2 -> Text(text = "Tab")
        }
    }
}

@Composable
fun HomeScreenTabs(){
    Surface {
        val context = LocalContext.current

        var selectedTabIndex by remember { mutableIntStateOf(0) }

        var apartmentList: List<ApartmentModel> by remember {
            mutableStateOf(emptyList())
        }

        var hotelList: List<HotelModel> by remember {
            mutableStateOf(emptyList())
        }

        val updateApartmentList: (List<ApartmentModel>) -> Unit = { newList ->

            apartmentList = newList
        }

        val updateHotelList: (List<HotelModel>) -> Unit = { newList ->

            hotelList = newList
        }

        getApartmentList(context, updateApartmentList)
        getHotelList(context, updateHotelList)

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary),
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier
                            .tabIndicatorOffset(tabPositions[selectedTabIndex])
                            .height(2.dp)
                    )
                }
            ) {
                Tab(
                    selected = selectedTabIndex == 0,
                    onClick = {
                        selectedTabIndex = 0; getApartmentList(
                        context,
                        updateApartmentList
                    )
                    },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Apartments") },
                    text = { Text("Apartments") }
                )
                Tab(
                    selected = selectedTabIndex == 1,
                    onClick = { selectedTabIndex = 1; getHotelList(context, updateHotelList) },
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
                0 -> ApartmentsTabContent(apartmentList)
                1 -> HotelsTabContent(hotelList)
                2 -> BookingsTabContent()
            }
        }
    }
}

@Composable
fun ApartmentsTabContent(
    apartmentList: List<ApartmentModel>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(apartmentList) { item ->

            ApartmentListItem(item, ::apartmentDetails)
        }
    }
}

@Composable
fun ApartmentListItem(
    apartment: ApartmentModel,
    onItemClick: (ApartmentModel, Context) -> Unit
) {
    var bitmap: Bitmap =
        if (imageFromBase64(apartment.pathImage) != null) {
            imageFromBase64(apartment.pathImage) as Bitmap
        } else {
            Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
        }

    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                onItemClick(apartment, context)
            }
    ) {
        Row(
            modifier = Modifier
                .defaultMinSize(minHeight = 100.dp)
                .fillMaxWidth()
        ) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Apartment",
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxHeight()
                    .width(100.dp)
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier
                .fillMaxHeight()
                .width(5.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(alignment = Alignment.CenterHorizontally),
                    text = apartment.name,
                    fontSize = 22.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = apartment.description,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun HotelsTabContent(
    hotelList: List<HotelModel>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(hotelList) { item ->

            HotelListItem(item)
        }
    }
}

@Composable
fun HotelListItem(
    hotel: HotelModel
)
{
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Icon(
            imageVector = Icons.Default.Home,
            contentDescription = "Hotel",
            modifier = Modifier
                .width(100.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.CenterHorizontally),
                text = hotel.name
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = hotel.description
            )
        }
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