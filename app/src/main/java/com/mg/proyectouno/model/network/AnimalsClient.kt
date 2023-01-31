package com.mg.proyectouno.model.network

import com.mg.proyectouno.model.entities.CallAnimal
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface AnimalsClient {

    @GET
    fun getAnimals(@Url url:String): Call<CallAnimal>
    
}