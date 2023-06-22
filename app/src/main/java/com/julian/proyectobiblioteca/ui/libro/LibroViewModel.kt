package com.julian.proyectobiblioteca.ui.libro

import androidx.lifecycle.*
import com.julian.proyectobiblioteca.model.DbFirestore
import com.julian.proyectobiblioteca.model.Libro
import kotlinx.coroutines.*

class LibroViewModel(): ViewModel() {
    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get() = _state

    init {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = _state.value?.copy(loading = true)
            DbFirestore.getAllObservable().observeForever {
                _state.value = _state.value?.copy(loading = false, libros = it)
            }
        }

    }

    private suspend fun requestLibros(): List<Libro>  = DbFirestore.getAll()

    fun navigateTo(libro: Libro) {
        _state.value = _state.value?.copy(navigateTo = libro)
    }

    fun onNavigateDone(){
        _state.value = _state.value?.copy(navigateTo = null)
    }

    fun navigateToCreate() {
        _state.value = _state.value?.copy(navigateToCreate = true)
    }

    fun navigateToCreateDone() {
        _state.value = _state.value?.copy(navigateToCreate = false)
    }

    data class UiState(
        val loading: Boolean = false,
        val libros: List<Libro>? = null,
        val navigateTo: Libro? = null,
        val navigateToCreate: Boolean = false
    )

}