package com.booking.app.api.models

import android.os.Parcel
import android.os.Parcelable

data class ApartmentModel (
    var id: Int = 0,
    var name: String = "",
    var city: String = "",
    var country: String = "",
    var phone: String = "",
    var capacity: Int = 0,
    var price: Int = 0,
    var description: String = "",
    var pathImage: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()?: "",
        parcel.readString()?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(city)
        parcel.writeString(country)
        parcel.writeString(phone)
        parcel.writeInt(capacity)
        parcel.writeInt(price)
        parcel.writeString(description)
        parcel.writeString(pathImage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ApartmentModel> {
        override fun createFromParcel(parcel: Parcel): ApartmentModel {
            return ApartmentModel(parcel)
        }

        override fun newArray(size: Int): Array<ApartmentModel?> {
            return arrayOfNulls(size)
        }
    }
}