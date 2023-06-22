package com.julian.proyectobiblioteca.ui.autor

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
import com.julian.proyectobiblioteca.databinding.FragmentAutorBinding
import com.julian.proyectobiblioteca.ui.autorDetail.AutorDetailFragment

class AutorFragment : Fragment(R.layout.fragment_autor) {

    private val viewModel: AutorViewModel by viewModels()
    private lateinit var binding: FragmentAutorBinding
    private val adapter = AdapterAutor(){ autor -> viewModel.navigateTo(autor)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAutorBinding.bind(view).apply {
            recycler.adapter = adapter
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

        viewModel.state.observe(viewLifecycleOwner){state ->
            binding.progress.visibility =  if (state.loading) VISIBLE else GONE
            state.autores?.let {
                adapter.autores = state.autores
                adapter.notifyDataSetChanged()
            }

            state.navigateTo?.let {
                findNavController().navigate(
                    R.id.action_autorFragment_to_autorDetailFragment,
                    bundleOf(AutorDetailFragment.EXTRA_AUTOR to it)
                )
                viewModel.onNavigateDone()
            }

            state.navigateToCreate?.let{
                if (it) {
                    findNavController().navigate(
                        R.id.action_autorFragment_to_createAutorFragment,
                    )
                    viewModel.navigateToCreateDone()
                }
            }

        }

        binding.addButton.setOnClickListener {
            viewModel.navigateToCreate()
        }

        binding.librosButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_autorFragment_to_libroFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("AutorFragment", "onDestroy")
    }
}