package com.julian.proyectobiblioteca.ui.libro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.julian.proyectobiblioteca.databinding.ViewLibroItemBinding
import com.julian.proyectobiblioteca.model.DbFirestore
import com.julian.proyectobiblioteca.model.Libro

class Adapter(val listener: (Libro) -> Unit):
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    var libros = emptyList<Libro>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewLibroItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val libro = libros[position]
        holder.bind(libro)

        holder.itemView.setOnClickListener {
            listener(libro)
        }

    }

    override fun getItemCount(): Int = libros.size

    class ViewHolder(private val binding: ViewLibroItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(libro: Libro) {

            binding.titulo.text = libro.titulo
            binding.fechaEstreno.text = libro.fechaEstreno
            binding.borrarLibroButton.setOnClickListener{
                DbFirestore.borraLibro(libro)
            }

            Glide
                .with(binding.root.context)
                .load(libro.portada)
                .into(binding.portada)

        }

    }

}