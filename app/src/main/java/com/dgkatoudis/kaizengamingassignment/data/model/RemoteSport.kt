package com.dgkatoudis.kaizengamingassignment.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteSport(
    @Json(name = "i")
    val id: String,
    @Json(name = "d")
    val title: String,
    @Json(name = "e")
    val events: List<RemoteSportEvent>
)
