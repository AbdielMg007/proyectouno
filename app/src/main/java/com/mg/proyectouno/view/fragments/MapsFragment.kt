package com.mg.proyectouno.view.fragments

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mg.proyectouno.R
import com.mg.proyectouno.databinding.FragmentMapsBinding
import com.mg.proyectouno.model.entities.Animal

class MapsFragment : Fragment(R.layout.fragment_maps), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var binding: FragmentMapsBinding
    private var nombre: String? = "Name"
    private var latitud: String? = "latitud"
    private var longitud: String? = "longitud"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nombre = requireArguments().getString(NOMBRE)
        latitud = requireArguments().getString(LATITUDMAP)
        longitud = requireArguments().getString(LONGITUDMAP)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMapsBinding.bind(view)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(gMap: GoogleMap) {
        map = gMap
        createMarker()
    }

    private fun createMarker(){

        var auxLatitud = 0.0
        var auxLongitud = 0.0

        if (latitud != null && longitud != null){
            auxLatitud = latitud!!.toDouble()
            auxLongitud  = longitud!!.toDouble()
        }


        val cordinates = LatLng(auxLatitud,auxLongitud)
        val marker = MarkerOptions().position(cordinates).title(nombre)
        map.addMarker(marker)
    }

    companion object{
        private const val NOMBRE = "Name"
        private const val LATITUDMAP = "Latitud"
        private const val LONGITUDMAP = "Longitud"

        fun newPass(name: String?, latitud: String?, longitud: String?) = MapsFragment().apply {
            arguments = bundleOf(NOMBRE to name, LATITUDMAP to latitud, LONGITUDMAP to longitud)
        }

    }
}