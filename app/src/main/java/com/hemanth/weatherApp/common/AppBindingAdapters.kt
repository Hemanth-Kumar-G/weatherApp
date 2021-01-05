package com.hemanth.weatherApp.common

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import java.text.SimpleDateFormat
import java.util.*


object AppBindingAdapters {

    @BindingAdapter("android:textDate")
    @JvmStatic
    fun setDate(view: TextView, timeStamp: Long) {
        val unix = timeStamp * 1000 /*if time stamp is of 10 digit*/
        val dateFormat = SimpleDateFormat("MMMM  dd yyyy");
        val dt = Date(unix)
        view.text = dateFormat.format(dt)
    }

    @BindingAdapter("onRefresh")
    @JvmStatic
    fun setRefresh(view: SwipeRefreshLayout, listener: SwipeRefreshLayout.OnRefreshListener) {
        val newValue = SwipeRefreshLayout.OnRefreshListener {
            view.isRefreshing = false
            listener.onRefresh()
        }
        view.setOnRefreshListener(newValue)
    }
}
