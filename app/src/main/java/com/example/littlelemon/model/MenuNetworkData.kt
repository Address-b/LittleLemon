package com.example.littlelemon.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuResponse(
    @SerialName("menu")
    val menu: List<MenuItem>
)