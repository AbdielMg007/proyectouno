package com.mg.proyectouno.model.entities

import java.io.Serializable


data class Animal (
    var id: Int,
    var nombre: String,
    var edad: Int,
    var sexo: Boolean,
    var foto: String,
    var notas: String,
    var latitud: Double,
    var longitud: Double

): Serializable


