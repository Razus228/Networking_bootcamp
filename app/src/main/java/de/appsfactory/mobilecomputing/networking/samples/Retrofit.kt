package de.appsfactory.mobilecomputing.networking

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*


private interface JsonPlaceholderApi {
    /**
     * Get list of all users
     */
    @GET("users")
    fun getUsers(): Call<String>

    /**
     * Get user with the given ID
     */
    @GET("users/{id}")
    fun getUserById(
        @Path("id") id: String
    ): Call<String>

    /**
     * Query user by username
     */
    @GET("users")
    fun getUserByUserName(@Query("username") userName: String): Call<String>

    @POST("users")
    fun createUser(
        @Header("Authorization") auth: String,
        @Body json: String
    ): Call<String>
}

class RetrofitNetworkRequest {
    fun doRequest(): String {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        val api = retrofit.create(JsonPlaceholderApi::class.java)
        return api.getUsers().execute().body().orEmpty()
    }
}

fun main() {
    val request = RetrofitNetworkRequest()
    println(request.doRequest())
}