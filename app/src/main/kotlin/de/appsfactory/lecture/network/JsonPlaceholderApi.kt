package de.appsfactory.lecture.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit = Retrofit.Builder()
    .baseUrl("https://jsonplaceholder.typicode.com/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

val jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi::class.java)

interface JsonPlaceholderApi {
    @GET("posts")
    suspend fun getPosts(): Response<List<ApiPost>>

    @GET("comments")
    suspend fun getComments(
        @Query("postId") postId: Long
    ): Response<List<ApiComment>>
}