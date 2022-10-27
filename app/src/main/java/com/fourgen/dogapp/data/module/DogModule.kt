package com.fourgen.dogapp.data.module

import com.google.gson.annotations.SerializedName

data class DogModule(
    @SerializedName("message") val message: List<String>,
    @SerializedName("status") val status: String
)