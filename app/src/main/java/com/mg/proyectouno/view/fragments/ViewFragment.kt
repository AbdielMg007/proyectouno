package com.mg.proyectouno.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.mg.proyectouno.R
import com.mg.proyectouno.databinding.FragmentViewBinding
import com.mg.proyectouno.model.entities.Animal
import com.squareup.picasso.Picasso

class ViewFragment : Fragment(R.layout.fragment_view) {

    private lateinit var binding: FragmentViewBinding

    private var nombre: String? = "Name"
    private var edad: String? = "age"
    private var sexo: String? = "false"
    private var foto: String? = "foto"
    private var nota: String? = "Note"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        nombre = requireArguments().getString(NAME)
        edad = requireArguments().getString(AGE)
        sexo = requireArguments().getString(SEX)
        foto = requireArguments().getString(PHOTO)
        nota = requireArguments().getString(NOTE)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentViewBinding.bind(view)
        binding.toolbarTitle.text = nombre
        binding.tvName.text = "Nombre: $nombre"
        binding.tvAge.text = "Edad: $edad"
        if (sexo == "false")
            binding.tvSex.text = "Sexo: Hembra"
        else
            binding.tvSex.text = "Sexo: Macho"
        binding.tvNote.text = nota
        Picasso.get()
            .load(foto)
            .placeholder(R.drawable.perro1)
            .error(R.drawable.gato)
            .into(binding.iPhoto)

        binding.toolbarReturned.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    companion object{
        private const val NAME = "Name"
        private const val AGE = "Age"
        private const val SEX = "false"
        private const val PHOTO = "foto"
        private const val NOTE = "Note"

        fun newInstance(animals: Animal) = ViewFragment().apply {
            arguments = bundleOf(NAME to animals.nombre, AGE to animals.edad.toString(), SEX to animals.sexo.toString(),
                PHOTO to animals.foto, NOTE to animals.notas)
        }

    }
}

