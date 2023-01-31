package com.mg.proyectouno.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mg.proyectouno.model.entities.Animal
import com.mg.proyectouno.model.network.ApiDbClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {
    var animalList: ArrayList<Animal> = arrayListOf()
    var list = MutableLiveData<ArrayList<Animal>>()

    init {
        getAnimalsList()
    }

    fun getAnimalsList() {
        CoroutineScope(Dispatchers.IO).launch{
            val call = ApiDbClient.service.getAnimals("api")
            val animals = call.execute().body()
            animalList = ((animals?.animals ?: emptyList<Animal>()) as ArrayList<Animal>)
            list.postValue(animalList)

        }
    }
}