<h1 align="center">DogApp</h1>

<p align="center">  
El proyecto consiste en una App donde se puede buscar la raza de perro
utilizando la siguiente API:
https://dog.ceo/dog-api/documentation/
</p>
</br>

<img src="/previews/preview.gif" align="right" width="320"/>

## Tecnologías utilizadas
- SDK mínimo 21
- SDK objetivo 32
- Basado en [Kotlin](https://kotlinlang.org/)
- Jetpack
    - Ciclo de vida: Observa los ciclos de vida de Android y maneja los estados de la interfaz de usuario en los cambios del ciclo de vida.
   - ViewModel: Administra el titular de datos relacionados con la interfaz de usuario y el ciclo de vida. Permite que los datos sobrevivan a los cambios de configuración, como las rotaciones de pantalla.
   - DataBinding: Vincula los componentes de la interfaz de usuario en sus diseños a las fuentes de datos en su aplicación utilizando un formato declarativo en lugar de programación.
  - [Hilt](https://dagger.dev/hilt/): Para inyección de dependencia.
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit): Construye las peticiones al API REST.
- [Glide](https://github.com/bumptech/glide), [GlidePalette](https://github.com/florent37/GlidePalette): Carga imágenes desde la red.
