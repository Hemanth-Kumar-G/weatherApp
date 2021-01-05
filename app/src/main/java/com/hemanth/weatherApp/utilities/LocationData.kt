package com.hemanth.weatherApp.utilities

data class LocationData(
    var postalCode: String="",
    var city: String="",
    var country: String="",
    var adminArea: String="",
    var locality: String="",
    var latitude: Double=0.0,
    var longitude: Double=0.0
) {
}