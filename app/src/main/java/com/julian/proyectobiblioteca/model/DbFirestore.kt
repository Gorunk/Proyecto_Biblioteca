package com.julian.proyectobiblioteca.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

object DbFirestore {
    const val COLLECTION_LIBROS = "libros"
    const val COLLECTION_AUTORES = "autores"
    suspend fun getAll(): List<Libro> {
        val snapshot = FirebaseFirestore.getInstance().collection(COLLECTION_LIBROS)
            .get()
            .await()
        val libros = mutableListOf<Libro>()
        for (documentSnapshot in snapshot){
            val libro = documentSnapshot.toObject(Libro::class.java)
            libros.add(libro)
        }
        return libros
    }

    suspend fun getAllAutores(): List<Autor> {
        val snapshot = FirebaseFirestore.getInstance().collection(COLLECTION_AUTORES)
            .get()
            .await()
        val autores = mutableListOf<Autor>()
        for (documentSnapshot in snapshot){
            val autor = documentSnapshot.toObject(Autor::class.java)
            autores.add(autor)
        }
        return autores
    }

    suspend fun createLibro(libro: Libro){
        FirebaseFirestore.getInstance().collection(COLLECTION_LIBROS)
            .add(libro)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    Log.d(COLLECTION_LIBROS, it.result.id)
                }
            }
            .addOnFailureListener {
                Log.e(COLLECTION_LIBROS, it.toString())
            }

    }

    suspend fun createAutor(autor: Autor){
        FirebaseFirestore.getInstance().collection(COLLECTION_AUTORES)
            .add(autor)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    Log.d(COLLECTION_AUTORES, it.result.id)
                }
            }
            .addOnFailureListener {
                Log.e(COLLECTION_AUTORES, it.toString())
            }

    }

    fun borraLibro(libro: Libro) {
        FirebaseFirestore.getInstance().collection(COLLECTION_LIBROS)
            .whereEqualTo("titulo", libro.titulo)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    val id = it.result.first().id
                    FirebaseFirestore.getInstance().collection(COLLECTION_LIBROS)
                        .document(id)
                        .delete()
                        .addOnCompleteListener {
                            if (it.isSuccessful){
                                Log.d(COLLECTION_LIBROS, id)
                            }
                        }
                        .addOnFailureListener {
                            Log.e(COLLECTION_LIBROS, it.toString())
                        }
                }
            }
    }

    fun borraAutor(autor: Autor) {
        FirebaseFirestore.getInstance().collection(COLLECTION_AUTORES)
            .whereEqualTo("nombre", autor.nombre)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    val id = it.result.first().id
                    FirebaseFirestore.getInstance().collection(COLLECTION_AUTORES)
                        .document(id)
                        .delete()
                        .addOnCompleteListener {
                            if (it.isSuccessful){
                                Log.d(COLLECTION_AUTORES, id)
                            }
                        }
                        .addOnFailureListener {
                            Log.e(COLLECTION_AUTORES, it.toString())
                        }
                }
            }
    }

    fun editarLibro(libro: Libro?, titulo: String, ) {
        FirebaseFirestore.getInstance().collection(COLLECTION_LIBROS)
            .whereEqualTo("titulo", titulo)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    val id = it.result.first().id
                    FirebaseFirestore.getInstance().collection(COLLECTION_LIBROS)
                        .document(id)
                        .update("fechaEstreno", libro?.fechaEstreno,
                             "sinopsis", libro?.sinopsis)
                        .addOnCompleteListener {
                            if (it.isSuccessful){
                                Log.d(COLLECTION_LIBROS, id)
                            }
                        }
                        .addOnFailureListener {
                            Log.e(COLLECTION_LIBROS, it.toString())
                        }
                }
            }
    }

    suspend fun getAllObservable(): LiveData<MutableList<Libro>> {

        return withContext(Dispatchers.IO) {
            val mutableData = MutableLiveData<MutableList<Libro>>()
            FirebaseFirestore.getInstance().collection(COLLECTION_LIBROS)
                .addSnapshotListener { snapshot, e ->
                    var listas = mutableListOf<Libro>()
                    if (snapshot != null) {
                        listas = snapshot.toObjects(Libro::class.java)
                    }
                    mutableData.value = listas
                }

            return@withContext mutableData
        }
    }

    suspend fun getAllObservableAutores(): LiveData<MutableList<Autor>> {

        return withContext(Dispatchers.IO) {
            val mutableData = MutableLiveData<MutableList<Autor>>()
            FirebaseFirestore.getInstance().collection(COLLECTION_AUTORES)
                .addSnapshotListener { snapshot, e ->
                    var listas = mutableListOf<Autor>()
                    if (snapshot != null) {
                        listas = snapshot.toObjects(Autor::class.java)
                    }
                    mutableData.value = listas
                }

            return@withContext mutableData
        }
    }


}