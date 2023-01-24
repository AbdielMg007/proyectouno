package com.mg.proyectouno.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mg.proyectouno.model.entities.Animal
import com.mg.proyectouno.model.repository.AnimalData

class ListViewModel : ViewModel() {
    val list = MutableLiveData<ArrayList<Animal>>()

    init {
        getAnimalsList()
    }

    fun getAnimalsList() {
        val animals = AnimalData.getData()
        list.postValue(animals)
    }
}