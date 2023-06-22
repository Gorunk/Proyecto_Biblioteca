package com.julian.proyectobiblioteca.ui.autorDetail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.julian.proyectobiblioteca.R
import com.julian.proyectobiblioteca.model.Autor

class AutorDetailFragment : Fragment(R.layout.fragment_autor_detail) {
    private val viewModel: AutorDetailViewModel by viewModels {
        AutorDetailViewModelFactory(arguments?.getParcelable<Autor>(EXTRA_AUTOR)!!)
    }
    companion object {
        const val EXTRA_AUTOR = "AutorDetailFragment:Autor"
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fotoAutor = view.findViewById<ImageView>(R.id.fotoAutor)
        val nombreAutor = view.findViewById<TextView>(R.id.nombreAutor)
        val wikiAutor = view.findViewById<TextView>(R.id.wikiAutor)

        viewModel.autor.observe(viewLifecycleOwner){ autor: Autor ->
            (requireActivity() as AppCompatActivity).supportActionBar?.title = autor.nombre
            Glide.with(this).load(autor.foto).into(fotoAutor)
            nombreAutor.text = autor.nombre
            wikiAutor.text = autor.wiki

        }

    }

}