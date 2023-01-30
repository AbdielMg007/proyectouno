package com.mg.proyectouno.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiDbClient {

    private val client = OkHttpClient.Builder().build()

    private val getRetrofit = Retrofit.Builder()
    .baseUrl("https://private-4e286a-abdielmg.apiary-mock.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .build()

    val service = getRetrofit.create(AnimalsClient::class.java)

}

