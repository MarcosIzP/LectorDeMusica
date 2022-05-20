package com.tfg.lectordemusica.dependencyinjection

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.tfg.lectordemusica.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

//Al importal "module", estamos diciendo de aquí se sacarán dependencias
@Module

//Mediante esta anotacion vamos a restringir el tiempo de vida de las dependencias creadas aquí al tiempo de vida que tenga la aplicación,
// por eso le pasamos "ApplicactionComponent"
@InstallIn(ApplicationComponent::class)

object AppModule {

    //A continuación se creará un manual para que Dagger sepa como crear las dependencias
    //Funcion que se va a utilizar a lo largo de toda la apliacion, ya que se trata de "el cargador de imágenes".
    // En el crearemos unos parámetros predeterminadps de cargapara cada vez que se necesite cargar una imagen simplemente hagmaos una anotación
    // a la que pasaremos como parámetros aquellos necesario para crear esta instancia
    @Singleton //Sine sta anotacion cada vez que se haga una petición a esa instancia Dagger creara una nueva instancia,
    // algo ineficiente ya que que con que se realice la primera instanciacion no son necesaria nuevas creaciones de instancia
    @Provides //para que dagger sepa que esta dependencia sirve para proveer algo
    fun provideGlideInstance(
       //Uno de los parámetros que necesitamos en el contexto de la aplicacion
        @ApplicationContext context : Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_image) // Por si no carga la imagen
            .error(R.drawable.ic_image) //en caso de error de la imagen
            .diskCacheStrategy(DiskCacheStrategy.DATA) //Si la imagen carga, glide la guardará en cache para proximas cargas
    )
}