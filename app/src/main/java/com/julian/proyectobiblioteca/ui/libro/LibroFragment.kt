package com.julian.proyectobiblioteca.ui.libro

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.julian.proyectobiblioteca.R
import com.julian.proyectobiblioteca.databinding.FragmentLibroBinding
import com.julian.proyectobiblioteca.ui.libroDetail.LibroDetailFragment

class LibroFragment : Fragment(R.layout.fragment_libro) {

    private val viewModel: LibroViewModel by viewModels()
    private lateinit var binding: FragmentLibroBinding
    private val adapter = Adapter(){ libro -> viewModel.navigateTo(libro)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLibroBinding.bind(view).apply {
            recycler.adapter = adapter
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

        viewModel.state.observe(viewLifecycleOwner){state ->
            binding.progress.visibility =  if (state.loading) VISIBLE else GONE
            state.libros?.let {
                adapter.libros = state.libros
                adapter.notifyDataSetChanged()
            }

            state.navigateTo?.let {
                findNavController().navigate(
                    R.id.action_libroFragment_to_libroDetailFragment,
                    bundleOf(LibroDetailFragment.EXTRA_LIBRO to it)
                )
                viewModel.onNavigateDone()
            }

            state.navigateToCreate?.let{
                if (it) {
                    findNavController().navigate(
                        R.id.action_libroFragment_to_createLibroFragment,
                    )
                    viewModel.navigateToCreateDone()
                }
            }

        }

        binding.addButton.setOnClickListener {
            viewModel.navigateToCreate()
        }

        binding.autoresButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_libroFragment_to_autorFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("LibroFragment", "onDestroy")
    }
}