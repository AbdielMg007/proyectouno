package com.mg.proyectouno.network

import com.mg.proyectouno.model.entities.Animal
import com.mg.proyectouno.model.repository.AnimalData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnimalService {
    suspend fun getData() : List<Animal> {
        return withContext(Dispatchers.IO) {
            val call = ApiDbClient.service.getAnimals("api").execute()
            call.body() ?: AnimalData.opData()
        } as List<Animal>
    }

}