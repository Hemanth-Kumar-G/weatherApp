package com.hemanth.weatherApp.utilities

import android.app.Activity
import android.content.Context
import android.location.Geocoder
import android.location.LocationManager
import android.util.Log
import java.io.IOException

private const val TAG = "LocationManagerUtil"
class LocationManagerUtil {

    companion object {
        private val locationData = LocationData()

        /**
         * this method
         * @param context
         * @return
         */
        fun getCurrentLocation(context: Activity): LocationData {
            val stringBuilder = StringBuilder()
            try {
                val lm =
                    context.applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                val geocoder = Geocoder(context.applicationContext)
                for (provider in lm.allProviders) {
                    val location = lm.getLastKnownLocation(provider)
                    if (location != null) {
                        try {
                            val addresses =
                                geocoder.getFromLocation(location.latitude, location.longitude, 1)
                            if (addresses != null && addresses.size > 0) {

                                locationData.latitude = addresses[0].latitude
                                locationData.longitude = addresses[0].longitude
                                locationData.locality = addresses[0].locality
                                locationData.adminArea = addresses[0].adminArea
                                locationData.postalCode = addresses[0].postalCode
                            }
                        } catch (e: IOException) {
                            e.printStackTrace()
                            Log.e(TAG, "getCurrentLocation: ${e.message}" )
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return locationData
        }
    }

}