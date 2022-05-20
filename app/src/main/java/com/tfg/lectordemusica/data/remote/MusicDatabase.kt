package com.tfg.lectordemusica.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.plcoding.spotifycloneyt.data.entities.Song
import com.plcoding.spotifycloneyt.otros.Constantes.COLLECION_CANCIONES
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class MusicDatabase {


    //constante de el tipo necesario para obtener datos de Firestore
    private val firestore = FirebaseFirestore.getInstance()

    //Gracias esto ahora podemos utilizaarlo para referneciar la coleccion de datos.
    // Aquí debemos introducir el nombre de nuestra colleccion,
    // que se hará mediante el objeto que hemos creado en el directorio de constantes que representa la colleccion
    private val songCollection = firestore.collection(COLLECION_CANCIONES)

    //Creacion de una funcion que obtenga todas las canciones de Firestore como una lista, del tipo Song,
    // que se trata del objeto que hemos creado primero con los campos correspondientes
    suspend fun obtenerCanciones(): List<Song> {
        return try {
            songCollection.get().await().toObjects(Song::class.java)
        } catch (e: Exception){
            emptyList()
        }
    }
    //Creamos una funcion suspendida. Esto hará que se ejecute de manera asincrona, no afectando así a la ejecucion del hilo principal.
    //Esto se hace así ya que es una llamada que se realiza a través de la red (es una peticion a Firebase), y puede tomar tiempo,
    // por lo que para no afectar a la ejecucion de la aplicacion se crea de esta manera


}