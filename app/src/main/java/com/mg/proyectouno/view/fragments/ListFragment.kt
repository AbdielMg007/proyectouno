package com.mg.proyectouno.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mg.proyectouno.R
import com.mg.proyectouno.databinding.FragmentListBinding
import com.mg.proyectouno.helpers.AnimalClick
import com.mg.proyectouno.model.entities.Animal
import com.mg.proyectouno.view.adapters.AdapterAnimals
import com.mg.proyectouno.viewmodel.ListViewModel

class ListFragment : Fragment(R.layout.fragment_list), AnimalClick {

    private lateinit var binding: FragmentListBinding
    private val animalViewModel: ListViewModel by viewModels()
    private lateinit var adapter: AdapterAnimals

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

        adapter = AdapterAnimals(arrayListOf(), this)

        binding.recyclerAnimals.layoutManager = LinearLayoutManager(context)
        binding.recyclerAnimals.adapter = adapter

        animalViewModel.list.observe(viewLifecycleOwner, androidx.lifecycle.Observer { newAnimals ->
            adapter.updateItems(newAnimals)
        })


    }

    override fun animalsClickListener(Animal: Animal) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, ViewFragment.newInstance(Animal))
            .addToBackStack("mainFragment")
            .commit()
    }


}