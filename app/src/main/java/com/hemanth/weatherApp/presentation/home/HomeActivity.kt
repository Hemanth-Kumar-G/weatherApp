package com.hemanth.weatherApp.presentation.home

import android.Manifest
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.hemanth.weatherApp.R
import com.hemanth.weatherApp.base.BaseActivity
import com.hemanth.weatherApp.common.Constants
import com.hemanth.weatherApp.data.model.WeatherResponse
import com.hemanth.weatherApp.databinding.ActivityHomeBinding
import com.hemanth.weatherApp.presentation.home.model.WeatherAdapter
import com.hemanth.weatherApp.utilities.LocationManagerUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    @Inject
    lateinit var adapter: WeatherAdapter

    @Inject
    lateinit var list: ArrayList<WeatherResponse.Data>

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.viewModel = viewModel
        viewModel.list = list
        checkLocationPermission()
        setupObserver()
        init()
    }

    private fun init() {
        binding.rvWeather.adapter = adapter
    }


    /**
     * <h2>setupObserver</h2>
     * this method is for observing the response from api
     */
    private fun setupObserver() {
        viewModel.eventList.observe(this, Observer {
            when (it.getContentIfNotPending()?.first) {
                Constants.SUCCESS -> notifyAdapter()
                Constants.ERROR -> showError(it.getContent().second.toString())
                Constants.NO_INTERNET -> showInternetError()
            }
        })
    }

    private fun showInternetError() {
        AlertDialog.Builder(this)
            .setMessage(getString(R.string.no_internet_error))
            .setPositiveButton(getString(R.string.retry)) { _, _ -> viewModel.getWeatherList() }
            .setOnCancelListener { viewModel.getWeatherList() }
            .show()
    }

    private fun showError(error: String) {
        val snackBar: Snackbar = Snackbar.make(binding.root, error, Snackbar.LENGTH_SHORT)
        snackBar.view.setBackgroundColor(
            ContextCompat.getColor(this, R.color.snackbar_error_background_color)
        )
        snackBar.show()
    }

    /**
     * <h2>notifyAdapter</h2>
     * this is method for notifying   adapter
     */
    private fun notifyAdapter() {
        adapter.notifyDataSetChanged()
    }

    /**
     * check permission for location
     */
    private fun checkLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                Constants.REQUEST_CODE_PERMISSION_MULTIPLE
            )
        } else {
            //permission granted.
            accessLocation()
        }
    }

    private fun accessLocation() {
        val locationData = LocationManagerUtil.getCurrentLocation(this)
        viewModel.lat.set(locationData.latitude)
        viewModel.lon.set(locationData.longitude)
        viewModel.getWeatherList()
    }

}