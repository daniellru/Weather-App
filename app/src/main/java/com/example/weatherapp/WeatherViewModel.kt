package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.api.Const
import com.example.weatherapp.api.NetWorkResponse
import com.example.weatherapp.api.RetrofitInstance
import com.example.weatherapp.api.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel() {

    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherResult = MutableLiveData<NetWorkResponse<WeatherModel>>()
    val weatherResult : LiveData<NetWorkResponse<WeatherModel>> = _weatherResult

    fun getData(city: String){

        viewModelScope.launch {
            _weatherResult.value = NetWorkResponse.Loading
            try {
                val response = weatherApi.getWeather(Const.API_KEY, city)
                if(response.isSuccessful){
                    response.body()?.let {
                        _weatherResult.value = NetWorkResponse.Success(it)
                    }
                }else{
                    _weatherResult.value = NetWorkResponse.Error("Failed to load data")
                }
            }
            catch (e: Exception){
                _weatherResult.value = NetWorkResponse.Error("Failed to load data")
            }
        }

    }
}