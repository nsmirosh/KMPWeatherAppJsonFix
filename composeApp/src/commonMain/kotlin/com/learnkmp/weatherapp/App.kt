package com.learnkmp.weatherapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.learnkmp.weatherapp.data.Forecast
import com.learnkmp.weatherapp.repository.WeatherRepository

@Composable
fun App() {
    MaterialTheme {
        val weatherRepository = remember { WeatherRepository() }
        var forecasts by remember { mutableStateOf<List<Forecast>?>(null) }

        LaunchedEffect(Unit) {
            forecasts = weatherRepository.getForecasts()
        }

        if (forecasts == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            WeatherScreen(forecasts!!)
        }
    }
}
