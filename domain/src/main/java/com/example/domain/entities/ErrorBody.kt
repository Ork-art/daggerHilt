package com.example.domain.entities

import com.google.gson.annotations.SerializedName

data class ErrorBody (
    @SerializedName("message")
    var errorMessage:String,
    @SerializedName("cod")
    val errorCode:Int
)