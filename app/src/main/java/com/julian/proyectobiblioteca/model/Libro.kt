package com.julian.proyectobiblioteca.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Libro(
    val titulo: String = "",
    val portada: String = "",
    val fechaEstreno: String = "",
    val sinopsis: String = ""
): Parcelable {
}