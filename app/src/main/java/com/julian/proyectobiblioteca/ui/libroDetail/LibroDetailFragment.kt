package com.julian.proyectobiblioteca.ui.libroDetail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.julian.proyectobiblioteca.R
import com.julian.proyectobiblioteca.model.DbFirestore
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.julian.proyectobiblioteca.model.Libro

class LibroDetailFragment : Fragment(R.layout.fragment_libro_detail) {
    private val viewModel: LibroDetailViewModel by viewModels {
        LibroDetailViewModelFactory(arguments?.getParcelable<Libro>(EXTRA_LIBRO)!!)
    }
    companion object {
        const val EXTRA_LIBRO = "LibroDetailFragment:Libro"
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val portadaLibro = view.findViewById<ImageView>(R.id.portadaLibro)
        val tituloLibro = view.findViewById<TextView>(R.id.tituloLibro)
        val fechaEstrenoLibro = view.findViewById<TextView>(R.id.fechaEstrenoLibro)
        val sinopsisLibro = view.findViewById<TextView>(R.id.sinopsisLibro)

        val editButton = view.findViewById<FloatingActionButton>(R.id.editButton)
        val editFechaEstrenoLibro = view.findViewById<EditText>(R.id.editFechaEstrenoLibro)
        val editSinopsisLibro = view.findViewById<EditText>(R.id.editSinopsisLibro)
        val editLibroButton = view.findViewById<Button>(R.id.editLibroButton)

        viewModel.libro.observe(viewLifecycleOwner){ libro: Libro ->
            (requireActivity() as AppCompatActivity).supportActionBar?.title = libro.titulo
            Glide.with(this).load(libro.portada).into(portadaLibro)
            tituloLibro.text = libro.titulo
            fechaEstrenoLibro.text = libro.fechaEstreno
            sinopsisLibro.text = libro.sinopsis

            editFechaEstrenoLibro.setText(libro.fechaEstreno)
            editSinopsisLibro.setText(libro.sinopsis)

            editButton.setOnClickListener {
                fechaEstrenoLibro.visibility = View.GONE
                sinopsisLibro.visibility = View.GONE

                editFechaEstrenoLibro.visibility = View.VISIBLE
                editSinopsisLibro.visibility = View.VISIBLE
                editLibroButton.visibility = View.VISIBLE
            }

            editLibroButton.setOnClickListener{
                val libroEditado = Libro(
                    tituloLibro.text.toString(),
                    "null",
                    editFechaEstrenoLibro.text.toString(),
                    editSinopsisLibro.text.toString(),

                )
                DbFirestore.editarLibro(libroEditado, tituloLibro.text.toString())
                findNavController().navigate(
                    R.id.action_libroDetailFragment_to_libroFragment)
            }

        }

    }

}