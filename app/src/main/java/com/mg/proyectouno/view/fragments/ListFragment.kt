package com.mg.proyectouno.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mg.proyectouno.R
import com.mg.proyectouno.databinding.FragmentListBinding
import com.mg.proyectouno.helpers.AnimalClick
import com.mg.proyectouno.model.entities.Animal
import com.mg.proyectouno.view.activities.LoginActivity
import com.mg.proyectouno.view.activities.MainActivity
import com.mg.proyectouno.view.adapters.AdapterAnimals
import com.mg.proyectouno.viewmodel.ListViewModel

class ListFragment : Fragment(R.layout.fragment_list), AnimalClick {

    private lateinit var binding: FragmentListBinding
    private val animalViewModel: ListViewModel by viewModels()
    private lateinit var adapter: AdapterAnimals
    var isFabOpen = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)



        adapter = AdapterAnimals(arrayListOf(), this)

        binding.recyclerAnimals.layoutManager = LinearLayoutManager(context)
        binding.recyclerAnimals.adapter = adapter

        animalViewModel.list.observe(viewLifecycleOwner) { newAnimals ->
            adapter.updateItems(newAnimals)
        }

        binding.apps.setOnClickListener {
            if (isFabOpen)
                closeFab()
            else
                showFab()
        }

        binding.profile.setOnClickListener{
            isFabOpen = false
            requireActivity().supportFragmentManager.commit {
                replace(R.id.fragmentContainerView, ProfileFragment())
                addToBackStack(null)
            }
        }

        binding.logOut.setOnClickListener {
            Firebase.auth.signOut()
            fragmentManager?.beginTransaction()
                ?.remove(this)
                ?.commit()
            val nextScreen = Intent(context, LoginActivity::class.java)
            startActivity(nextScreen)
        }

    }

    fun showFab(){
        isFabOpen = true
        binding.apps.setImageResource(R.drawable.close)
        binding.profile.animate().translationY(-360F)
        binding.logOut.animate().translationY(-180F)
    }

    fun closeFab(){
        isFabOpen = false
        binding.apps.setImageResource(R.drawable.apps)
        binding.profile.animate().translationY(0F)
        binding.logOut.animate().translationY(0F)
    }


    override fun animalsClickListener(Animal: Animal) {
        isFabOpen = false
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, ViewFragment.newInstance(Animal))
            .addToBackStack("mainFragment")
            .commit()
    }


}