package com.hemanth.weatherApp.common

import okio.IOException

class NoConnectivityException : IOException() {
    override val message: String?
        get() = "No Internet Connection"
}