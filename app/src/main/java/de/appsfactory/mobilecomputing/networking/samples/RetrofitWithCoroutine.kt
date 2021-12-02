package de.appsfactory.mobilecomputing.networking

import android.os.AsyncTask
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private interface JsonPlaceholderApiWithCoroutine {
    @GET("users")
    suspend fun getUsers(): String
}

class RetrofitWithCoroutine {
    suspend fun doRequest(): String {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        val api = retrofit.create(JsonPlaceholderApiWithCoroutine::class.java)
        return api.getUsers()
    }
}

fun main() = runBlocking {
    val request = RetrofitWithCoroutine()
    val result = request.doRequest()
    println(result)
}