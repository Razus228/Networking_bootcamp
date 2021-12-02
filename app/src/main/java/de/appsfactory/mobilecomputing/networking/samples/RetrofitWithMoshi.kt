package de.appsfactory.mobilecomputing.networking

import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private interface JsonPlaceholderApiWithMoshi {
    @GET("users")
    suspend fun getUsers(): List<User>
}

class RetrofitWithMoshi {
    suspend fun doRequest(): List<User> {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        val api = retrofit.create(JsonPlaceholderApiWithMoshi::class.java)
        return api.getUsers()
    }
}

fun main() = runBlocking {
    val request = RetrofitWithMoshi()
    println(request.doRequest())
}