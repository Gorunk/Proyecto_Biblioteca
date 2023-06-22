package com.julian.proyectobiblioteca.ui.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julian.proyectobiblioteca.model.DbFirestore
import com.julian.proyectobiblioteca.model.Libro
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateLibroViewModel: ViewModel() {

    fun createLibro(libro: Libro){
        viewModelScope.launch(Dispatchers.IO) {
            DbFirestore.createLibro(libro)
        }

    }
}