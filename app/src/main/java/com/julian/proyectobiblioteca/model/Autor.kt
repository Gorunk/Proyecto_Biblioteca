package com.julian.proyectobiblioteca.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Autor(
    val nombre: String = "",
    val foto: String = "",
    val wiki: String = "",
): Parcelable {
}