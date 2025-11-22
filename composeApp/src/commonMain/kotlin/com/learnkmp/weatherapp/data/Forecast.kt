package com.learnkmp.weatherapp.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Forecast(
    val city: String,
    @SerialName("tempC") val temperatureInCelcius: Int = 0,
    val humidity: Int,
    val description: String = "Not available"
)
