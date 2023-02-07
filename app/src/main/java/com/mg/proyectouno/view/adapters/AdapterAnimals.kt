package com.mg.proyectouno.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mg.proyectouno.R
import com.mg.proyectouno.databinding.AnimalsCardBinding
import com.mg.proyectouno.helpers.AnimalClick
import com.mg.proyectouno.model.entities.Animal
import com.squareup.picasso.Picasso

class AdapterAnimals(private var animal: ArrayList<Animal>, private val AnimalClick: AnimalClick) : RecyclerView.Adapter<AdapterAnimals.AnimalViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val binding = AnimalsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimalViewHolder(binding)
    }


    override fun onBindViewHolder(holder: AnimalViewHolder, i: Int) {
        holder.bind(animal[i])
        holder.itemView.setOnClickListener{
            AnimalClick.animalsClickListener(animal[i])
        }
    }

    override fun getItemCount(): Int = animal.size

    class AnimalViewHolder(val binding: AnimalsCardBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(animal: Animal) {
            binding.itemName.text = animal.nombre
            Picasso.get()
                .load(animal.foto)
                .placeholder(R.drawable.error)
                .error(R.drawable.error)
                .into(binding.itemImage)
        }
    }

    fun updateItems(newItems: ArrayList<Animal>) {
        animal.clear()
        animal.addAll(newItems)
        notifyDataSetChanged()
    }

}