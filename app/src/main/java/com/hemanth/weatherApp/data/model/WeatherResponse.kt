package com.hemanth.weatherApp.data.model


import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("data")
    var `data`: List<Data>
) {
    data class Data(
        @SerializedName("rain")
        var rain: Int,
        @SerializedName("temp")
        var temp: Int,
        @SerializedName("time")
        var time: Int,
        @SerializedName("wind")
        var wind: Int
    )
}