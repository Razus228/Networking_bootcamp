package de.appsfactory.mobilecomputing.networking

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/*

  {
    "id": 1,
    "name": "Leanne Graham",
    "username": "Bret",
    "email": "Sincere@april.biz",
    "address": {
      "street": "Kulas Light",
      "suite": "Apt. 556",
      "city": "Gwenborough",
      "zipcode": "92998-3874",
      "geo": {
        "lat": "-37.3159",
        "lng": "81.1496"
      }
    },
    "phone": "1-770-736-8031 x56442",
    "website": "hildegard.org",
    "company": {
      "name": "Romaguera-Crona",
      "catchPhrase": "Multi-layered client-server neural-net",
      "bs": "harness real-time e-markets"
    }

 */

@JsonClass(generateAdapter = true)
data class Address(
    val street: String,
    val city: String,
    @Json(name = "zipcode")
    val zipCode: String
)

@JsonClass(generateAdapter = true)
data class User(
    val name: String,
    val username: String,
    val address: Address
)