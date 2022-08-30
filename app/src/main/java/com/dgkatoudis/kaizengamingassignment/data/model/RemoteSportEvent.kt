package com.dgkatoudis.kaizengamingassignment.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteSportEvent(
    @Json(name = "i")
    val id: String,
    @Json(name = "d")
    val name: String,
    @Json(name = "si")
    val sportId: String,
    @Json(name = "tt")
    val epoch: Long
)