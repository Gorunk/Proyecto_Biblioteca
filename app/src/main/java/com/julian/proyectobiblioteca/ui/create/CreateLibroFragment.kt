package com.julian.proyectobiblioteca.ui.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.julian.proyectobiblioteca.R
import com.julian.proyectobiblioteca.databinding.FragmentCreateLibroBinding
import com.julian.proyectobiblioteca.model.Libro

class CreateLibroFragment : Fragment(R.layout.fragment_create_libro) {
    private val viewModel: CreateLibroViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCreateLibroBinding.bind(view)

        binding.button.setOnClickListener {
            val libro = Libro(
                binding.editTextTextLibroTitle.text.toString(),
                binding.editTextTextLibroFrontpage.text.toString(),
                binding.editTextTextLibroPremiereDate.text.toString(),
                binding.editTextTextLibroSynopsis.text.toString(),
            )
            viewModel.createLibro(libro)
            findNavController().navigate(
                R.id.action_createLibroFragment_to_libroFragment)
        }
    }
}