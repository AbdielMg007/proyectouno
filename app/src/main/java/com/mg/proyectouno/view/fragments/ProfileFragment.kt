package com.mg.proyectouno.view.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mg.proyectouno.R
import com.mg.proyectouno.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)
        setup()
    }

    fun setup(){
        auth = Firebase.auth
        val user = Firebase.auth.currentUser
        user?.let {
            val email = it.email
            binding.tvNameUser.text = email
        }
        binding.toolbarReturned.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

}

