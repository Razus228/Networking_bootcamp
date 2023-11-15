package de.appsfactory.lecture.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiPost(
    @Json(name = "id") val id: Long,
    @Json(name = "title") val title: String,
    @Json(name = "body") val body: String,
)
