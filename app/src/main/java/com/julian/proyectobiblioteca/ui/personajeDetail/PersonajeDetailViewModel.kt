package com.julian.proyectobiblioteca.ui.autorDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.julian.proyectobiblioteca.model.Autor

class AutorDetailViewModel(autor: Autor): ViewModel() {
    private val _autor = MutableLiveData(autor)
    val autor: LiveData<Autor> get() = _autor
}

@Suppress("UNCHECKED_CAST")
class AutorDetailViewModelFactory(private val autor: Autor): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AutorDetailViewModel(autor) as T
    }

}