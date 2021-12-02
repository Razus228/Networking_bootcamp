package de.appsfactory.mobilecomputing.networking.samples

import de.appsfactory.mobilecomputing.networking.BASE_URL
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class PlainNetworkRequest {

    fun doRequest(): String {

        val url = URL(BASE_URL)
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"

        val reader = BufferedReader(InputStreamReader(connection.inputStream))
        var inputLine: String?
        val content = StringBuffer()
        while (reader.readLine().also { inputLine = it } != null) {
            content.append(inputLine)
        }
        reader.close()

        return content.toString()
    }
}

fun main() {
    val request = PlainNetworkRequest()
    val result = request.doRequest()
    println(result)
}