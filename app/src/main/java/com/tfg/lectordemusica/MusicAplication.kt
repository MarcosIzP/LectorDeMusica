package com.tfg.lectordemusica

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//Siempre que se quiera utlizar Dagger hay que instanciar lo siguinte.
// Al instanciarlo, esta actividad se marcará como pricial para declarar las dependencias de Dagger. Para ello tambíen hay que cambiar la actividad principal a esto en el "android-manifiest"
@HiltAndroidApp

class MusicAplication : Application()