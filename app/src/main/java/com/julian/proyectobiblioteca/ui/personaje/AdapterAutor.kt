package com.julian.proyectobiblioteca.ui.autor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.julian.proyectobiblioteca.databinding.ViewAutorItemBinding
import com.julian.proyectobiblioteca.model.DbFirestore
import com.julian.proyectobiblioteca.model.Autor

class AdapterAutor(val listener: (Autor) -> Unit):
    RecyclerView.Adapter<AdapterAutor.ViewHolder>() {

    var autores = emptyList<Autor>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewAutorItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val autor = autores[position]
        holder.bind(autor)

        holder.itemView.setOnClickListener {
            listener(autor)
        }

    }

    override fun getItemCount(): Int = autores.size

    class ViewHolder(private val binding: ViewAutorItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(autor: Autor) {

            binding.nombreAutor.text = autor.nombre
            binding.borrarAutorButton.setOnClickListener{
                DbFirestore.borraAutor(autor)
            }

            Glide
                .with(binding.root.context)
                .load(autor.foto)
                .into(binding.foto)

        }

    }

}