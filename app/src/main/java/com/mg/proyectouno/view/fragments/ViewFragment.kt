package com.mg.proyectouno.view.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.mg.proyectouno.R
import com.mg.proyectouno.databinding.FragmentViewBinding
import com.mg.proyectouno.model.entities.Animal

class ViewFragment : Fragment(R.layout.fragment_view) {

    private lateinit var binding: FragmentViewBinding

    private var type: String? = "type"
    private var nombre: String? = "Name"
    private var foto: String? = "Photo"
    private var edad: String? = "age"
    private var nota: String? = "Sex"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        type = requireArguments().getString(TYPE)
        nombre = requireArguments().getString(NAME)
        foto = requireArguments().getString(PHOTO)
        edad = requireArguments().getString(AGE)
        nota = requireArguments().getString(NOTE)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentViewBinding.bind(view)

        binding.toolbarTitle.text = nombre
        binding.iPhoto.setImageResource(foto!!.toInt())
        binding.tvName.text = "Nombre: $nombre"
        binding.tvType.text = "Especie: $type"
        binding.tvAge.text = "Edad: $edad"
        binding.tvNote.text = nota


        binding.toolbarReturned.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    companion object{
        private const val TYPE = "Type"
        private const val NAME = "Name"
        private const val PHOTO = "Photo"
        private const val AGE = "Age"
        private const val NOTE = "Note"

        fun newInstance(animals: Animal) = ViewFragment().apply {
            arguments = bundleOf(TYPE to animals.type, NAME to animals.name, PHOTO to animals.photo.toString(), AGE to animals.age.toString(), NOTE to animals.note)
        }

    }
}

