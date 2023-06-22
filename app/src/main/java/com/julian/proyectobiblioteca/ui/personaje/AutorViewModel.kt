package com.julian.proyectobiblioteca.ui.autor

import androidx.lifecycle.*
import com.julian.proyectobiblioteca.model.DbFirestore
import com.julian.proyectobiblioteca.model.Autor
import kotlinx.coroutines.*

class AutorViewModel(): ViewModel() {
    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get() = _state

    init {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = _state.value?.copy(loading = true)
            DbFirestore.getAllObservableAutores().observeForever {
                _state.value = _state.value?.copy(loading = false, autores = it)
            }
        }

    }

    private suspend fun requestAutores(): List<Autor>  = DbFirestore.getAllAutores()

    fun navigateTo(autor: Autor) {
        _state.value = _state.value?.copy(navigateTo = autor)
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
        val autores: List<Autor>? = null,
        val navigateTo: Autor? = null,
        val navigateToCreate: Boolean = false
    )

}