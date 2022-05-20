package com.plcoding.spotifycloneyt.data.entities

data class Song(
    //En esta clase dclararemos los campos que se corresponden con los que tiene cada canción en la base de datos.
    // Tienen que ser los mismos para que cuando FIrenbase haga la comprobacion, sepa de que campos estamos hablando
    //Para que Firebase detecte estos campos hay que ponerles valor. Por defecto, lo mejor es darles el valor vacío

    val media_id: String = "",
    val nombre: String = "",
    val artista: String = "",
    val genero: String = "",
    val genero2: String = "",
    val portadaURL: String = "",
    val songURL: String = ""

)