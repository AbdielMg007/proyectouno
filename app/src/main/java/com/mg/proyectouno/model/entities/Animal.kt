package com.mg.proyectouno.model.entities

import java.io.Serializable


data class Animal (var type: String,
    var name: String,
    var photo: Int,
    var age: Int,
    var note: String
): Serializable


