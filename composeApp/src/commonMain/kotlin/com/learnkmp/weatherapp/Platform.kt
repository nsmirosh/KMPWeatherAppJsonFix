package com.learnkmp.weatherapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform