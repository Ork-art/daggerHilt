package com.example.core.extencion

import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest

fun NavController.deeplinkNavigate(direction: String, extras: MutableMap<String, String>? = null) {
    var deeplinkDirection = direction
    extras?.forEach { (key, value) ->
        deeplinkDirection = deeplinkDirection.replace("{$key}", value)
    }
    val deepLink = NavDeepLinkRequest.Builder
        .fromUri(deeplinkDirection.toUri())
        .build()
    navigate(deepLink)
}