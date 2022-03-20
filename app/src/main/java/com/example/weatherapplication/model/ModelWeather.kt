package com.example.weatherapplication.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

//data class WeatherModel(
//    var weatherArr: List<ModelWeather>
//)

data class ModelWeather(
    @SerializedName("message")
    var message: String,
    @SerializedName("cod")
    var cod: String,
    @SerializedName("count")
    var count: Int,
    @SerializedName("list")
    var list: List<WeatherList>
)

data class WeatherList(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String?,
    @SerializedName("coord")
    var coord: Coord?,
    @SerializedName("main")
    var main: Main?,
    @SerializedName("dt")
    var dt:Int,
    @SerializedName("wind")
    var wind: Wind?,
    @SerializedName("sys")
    var sys: Sys?,
//    @SerializedName("rain")
//    var rain: String,
//    @SerializedName("snow")
//    var snow: String,
    @SerializedName("clouds")
    var clouds: Clouds?,
    @SerializedName("weather")
    var weather: ArrayList<Weather>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readParcelable(Coord::class.java.classLoader),
        parcel.readParcelable(Main::class.java.classLoader),
        parcel.readInt(),
        parcel.readParcelable(Wind::class.java.classLoader),
        parcel.readParcelable(Sys::class.java.classLoader),
        parcel.readParcelable(Clouds::class.java.classLoader),
        parcel.createTypedArrayList(Weather)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeParcelable(coord, flags)
        parcel.writeParcelable(main, flags)
        parcel.writeInt(dt)
        parcel.writeParcelable(wind, flags)
        parcel.writeParcelable(sys, flags)
        parcel.writeParcelable(clouds, flags)
        parcel.writeTypedList(weather)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeatherList> {
        override fun createFromParcel(parcel: Parcel): WeatherList {
            return WeatherList(parcel)
        }

        override fun newArray(size: Int): Array<WeatherList?> {
            return arrayOfNulls(size)
        }
    }
}

data class Coord(
    @SerializedName("lat")
    var lat: Double,
    @SerializedName("Lon")
    var lon: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(lat)
        parcel.writeDouble(lon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Coord> {
        override fun createFromParcel(parcel: Parcel): Coord {
            return Coord(parcel)
        }

        override fun newArray(size: Int): Array<Coord?> {
            return arrayOfNulls(size)
        }
    }
}

data class Main(
    @SerializedName("temp")
    var temp: Double,
    @SerializedName("feels_like")
    var feels_like: Double,
    @SerializedName("temp_min")
    var temp_min: Double,
    @SerializedName("temp_max")
    var temp_max: Double,
    @SerializedName("pressure")
    var pressure: Int,
    @SerializedName("humidity")
    var humidity: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(temp)
        parcel.writeDouble(feels_like)
        parcel.writeDouble(temp_min)
        parcel.writeDouble(temp_max)
        parcel.writeInt(pressure)
        parcel.writeInt(humidity)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Main> {
        override fun createFromParcel(parcel: Parcel): Main {
            return Main(parcel)
        }

        override fun newArray(size: Int): Array<Main?> {
            return arrayOfNulls(size)
        }
    }
}

data class Wind(
    @SerializedName("speed")
    var speed: Double,
    @SerializedName("deg")
    var deg: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(speed)
        parcel.writeDouble(deg)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Wind> {
        override fun createFromParcel(parcel: Parcel): Wind {
            return Wind(parcel)
        }

        override fun newArray(size: Int): Array<Wind?> {
            return arrayOfNulls(size)
        }
    }
}

data class Sys(
    @SerializedName("country")
    var country: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(country)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Sys> {
        override fun createFromParcel(parcel: Parcel): Sys {
            return Sys(parcel)
        }

        override fun newArray(size: Int): Array<Sys?> {
            return arrayOfNulls(size)
        }
    }
}

data class Clouds(
    @SerializedName("all")
    var all: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(all)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Clouds> {
        override fun createFromParcel(parcel: Parcel): Clouds {
            return Clouds(parcel)
        }

        override fun newArray(size: Int): Array<Clouds?> {
            return arrayOfNulls(size)
        }
    }
}

data class Weather(
    @SerializedName("id")
    var id: Int,
    @SerializedName("main")
    var main: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("icon")
    var icon: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(main)
        parcel.writeString(description)
        parcel.writeString(icon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Weather> {
        override fun createFromParcel(parcel: Parcel): Weather {
            return Weather(parcel)
        }

        override fun newArray(size: Int): Array<Weather?> {
            return arrayOfNulls(size)
        }
    }
}
