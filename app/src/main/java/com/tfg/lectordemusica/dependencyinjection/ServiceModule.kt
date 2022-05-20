package com.tfg.lectordemusica.dependencyinjection

import android.content.Context
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped

//En este módulo vamos a encargarnos del reproductor de audio con "exoplayer"
@Module

//En esta ocasion restringimos el tiempo de vida, al tiempo que el servicio esté activo, que será el servicio de escucha de música
@InstallIn(ServiceComponent::class)
object ServiceModule {

    //Anotacion parecida a "@singletone" que se encarga de que siempre que se necesite la ejecución de el servicio de música,
    // se ejecute esa misma dependencia siempre
    @ServiceScoped
    //para que dagger sepa que esta dependencia sirve para proveer algo
    @Provides
    //Funcion que guardará metadatos sobre el reproductor de música, que no necesita parámetros
    fun AudioAttributes() = AudioAttributes.Builder()
        .setContentType(C.CONTENT_TYPE_MUSIC) //Declaramos que tipo de contenido utiliza este servicio
        .setUsage(C.USAGE_MEDIA)
        .build()

    @ServiceScoped
    @Provides
    //Funcion que proveerá al servicio de un reproductor. Esta si necesita algunas dependencias
    fun provideExoPlayer(
        //el contexto de la aplicacion
        @ApplicationContext context: Context,
        //Y el reproductor de audio que, Dagger-Hilt,
        // es capaz de crear automáticamente gracias a que le acabamos de pasar un manual que declara los datos necesarios para que se cree el reproductor "exoplayer"
        audioAttributes: AudioAttributes
    ) = SimpleExoPlayer.Builder(context).build().apply {
        setAudioAttributes(audioAttributes, true)
        setHandleAudioBecomingNoisy(true)
    }

    @ServiceScoped
    @Provides
    //debido a que queremos obtener la música de la database de Firebase,
    // debemos crear una función que proveera de el origen de los datos de la música
    //Necesita un parámetro, que es el contexto
    fun provideDataSourceFactory(
        @ApplicationContext context: Context
    ) = DefaultDataSourceFactory(context, Util.getUserAgent(context, "NowCast"))


}