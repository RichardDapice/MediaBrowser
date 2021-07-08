<h1 align="center">Media Browser</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=26"><img alt="API" src="https://img.shields.io/badge/API-26%2B-brightgreen.svg?style=flat"/></a>
</p>

<p align="center">  
Media Browser is a WIP application based on modern Android development and MVVM architecture utlizing Hilt for dependency inject along with flow and stateFlow for service and state management.
</p>
</br>

<p align="center">
<img src="/screenshots/screenshot.png" align="right"  width="22%"/>
</p>

## Details
- Minimum SDK level 26
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Hilt](https://dagger.dev/hilt/) for dependency injection.
- JetPack
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
- Architecture
  - MVVM Architecture
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit)
- [Moshi](https://github.com/square/moshi/)
- [Glide](https://github.com/bumptech/glide)
- [Timber](https://github.com/JakeWharton/timber)
- [Spotless Grade](https://github.com/diffplug/spotless)


## MAD Score
<p align="center">
<img src="/screenshots/madScore.png" />
</p>

## Kotlin Score
<p align="center">
<img src="/screenshots/kotlin.png" />
</p>

// Todos
- Testing with Turbine
- Consolidate list of media within ViewModel
- Details Fragment
