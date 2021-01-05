package com.hemanth.weatherApp.presentation.home

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hemanth.weatherApp.base.BaseViewModel
import com.hemanth.weatherApp.common.Constants
import com.hemanth.weatherApp.common.NoConnectivityException
import com.hemanth.weatherApp.data.model.WeatherResponse
import com.hemanth.weatherApp.data.repository.HomeRepository
import com.hemanth.weatherApp.utilities.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class HomeViewModel @ViewModelInject constructor(
    private val homeRepository: HomeRepository
) : BaseViewModel() {

    val loading = ObservableBoolean(true)
    val lat = ObservableField<Double>()
    val lon = ObservableField<Double>()
    var list: ArrayList<WeatherResponse.Data>? = null

    private val _eventList = MutableLiveData<Event<Pair<String, *>>>()
    val eventList: LiveData<Event<Pair<String, *>>> = _eventList


    fun getWeatherList() {
        setLoading(true)
        val disposableObserver =
            object : DisposableSingleObserver<Response<WeatherResponse>>() {
                override fun onSuccess(value: Response<WeatherResponse>) {
                    when (value.code()) {
                        200 -> {
                              saveResponse(value.body() as WeatherResponse)
                              _eventList.postValue(
                                  Event(Pair(Constants.SUCCESS, value.body()))
                              )
                        }
                        else -> _eventList.postValue(
                            Event(Pair(Constants.ERROR, value.code().toString()))
                        )
                    }
                    setLoading(false)
                }

                override fun onError(e: Throwable) {
                    setLoading(false)
                    if (e is NoConnectivityException)
                        _eventList.postValue(Event(Pair(Constants.NO_INTERNET, e.message)))
                    else
                        _eventList.postValue(Event(Pair(Constants.ERROR, e.message)))
                }
            }
        homeRepository.getDetails().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(disposableObserver)
    }

    private fun saveResponse(response: WeatherResponse?) {
        list?.clear()
        response?.data?.let { list?.addAll(it) }
    }

    fun setLoading(isLoading: Boolean) {
        loading.set(isLoading)
    }

    fun onRefresh() {
        getWeatherList()
    }

}