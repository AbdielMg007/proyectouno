package com.mg.proyectouno.model.repository

import android.util.Log
import com.mg.proyectouno.model.entities.Animal
import com.mg.proyectouno.network.ApiDbClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnimalData {
    companion object {

        val list: ArrayList<Animal> = arrayListOf()

        fun opData(): ArrayList<Animal> {
            return list
        }


    }
}




