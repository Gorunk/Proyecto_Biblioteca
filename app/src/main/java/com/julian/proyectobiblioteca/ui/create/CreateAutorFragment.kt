package com.julian.proyectobiblioteca.ui.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.julian.proyectobiblioteca.R
import com.julian.proyectobiblioteca.databinding.FragmentCreateAutorBinding
import com.julian.proyectobiblioteca.model.Autor

class CreateAutorFragment : Fragment(R.layout.fragment_create_autor) {
    private val viewModel: CreateAutorViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCreateAutorBinding.bind(view)

        binding.button.setOnClickListener {
            val autor = Autor(
                binding.editTextTextAutorNombre.text.toString(),
                binding.editTextTextAutorFoto.text.toString(),
                binding.editTextTextAutorWiki.text.toString(),
            )
            viewModel.createAutor(autor)
            findNavController().navigate(
                R.id.action_createAutorFragment_to_autorFragment)
        }
    }
}