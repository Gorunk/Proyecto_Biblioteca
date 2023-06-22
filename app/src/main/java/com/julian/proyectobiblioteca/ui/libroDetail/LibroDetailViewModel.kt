package com.julian.proyectobiblioteca.ui.libroDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.julian.proyectobiblioteca.model.Libro

class LibroDetailViewModel(libro: Libro): ViewModel() {
    private val _libro = MutableLiveData(libro)
    val libro: LiveData<Libro> get() = _libro
}

@Suppress("UNCHECKED_CAST")
class LibroDetailViewModelFactory(private val libro: Libro): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LibroDetailViewModel(libro) as T
    }

}