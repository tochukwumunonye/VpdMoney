package com.example.vpdmoney.data.remote.api

import com.example.vpdmoney.data.remote.dto.UsersDtoItem
import retrofit2.http.GET

interface APIService {

    @GET("/users")
    suspend fun getUsers(): ArrayList<UsersDtoItem>

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }
}
