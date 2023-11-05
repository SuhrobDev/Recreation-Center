package dev.soul.recreationcenter.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class MainResponse<T>(
    val data: T,
    val success: Boolean,
    val error: T,
    val time: String

)