package com.julian.proyectobiblioteca.ui.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julian.proyectobiblioteca.model.DbFirestore
import com.julian.proyectobiblioteca.model.Autor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateAutorViewModel: ViewModel() {

    fun createAutor(autor: Autor){
        viewModelScope.launch(Dispatchers.IO) {
            DbFirestore.createAutor(autor)
        }

    }
}