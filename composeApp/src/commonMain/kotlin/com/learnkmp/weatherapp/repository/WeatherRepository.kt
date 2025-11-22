package com.learnkmp.weatherapp.repository

import com.learnkmp.weatherapp.data.Forecast
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.call.body
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

val json = """[
                {
                    "city": "London",
                    "tempC": 15,
                    "humidity": 70,
                    "description": "Cloudy"
                },
                {
                    "city": "New York",
                    "tempC": 22,
                    "humidity": 55,
                    "description": null
                },
                {
                    "city": "Tokyo",
                    "tempC": 18,
                    "humidity": 65,
                    "description": "Partly cloudy",
                },
                {
                    "city": "Sydney",
                    tempC: 25,
                    "humidity": 60,
                    "description": "Clear skies", 
                    "weatherIcon": "https://cdn.weatherapi.com/weather/64x64/day/116.png"
                }
            ]"""

class WeatherRepository {
    private val mockEngine = MockEngine { request ->
        respond(
            content = ByteReadChannel(json.encodeToByteArray()),
            headers = headersOf(HttpHeaders.ContentType, "application/json")
        )
    }

    @OptIn(ExperimentalSerializationApi::class)
    private val client = HttpClient(mockEngine) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                allowTrailingComma = true
                isLenient = true
                coerceInputValues = true
            })
        }
    }

    suspend fun getForecasts(): List<Forecast> {
        return client.get("/weather").body()
    }
}

