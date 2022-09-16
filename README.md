# VpdMoney

## Table of content
- [Prerequisite](#prerequisite)
- [Preview](#preview)



## Prerequisite
- Android Studio Arctic Fox | 2020.3.1
- Gradle version 7.0.2
- MinSdk 21
- TargetSdk 32




## Architecture
Here are some architecture and technical decisions I made:
- I leveraged on constraint Layout to avoid nested layouts which can cause UI jank
- I followed clean architecture(Data, Domain, Presentation) guidelines to ensure separation of concerns whichmakes the application is scalable, testable and mainainable
- I used coroutines to prevent memory leaks and threading issues.



## Testing
Testing is done with Junit4 testing framework for assertions and Mockito for mocking classes and Expresso for Instrumented Test. Each  layer has its own test. 
Viewmodel tests verify that each call to repository produces the correct view state.
Repository Test verify each interaction with database returns the expected result.
UI  was  tested with Expresso

## - Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [StateFlows](https://developer.android.com/kotlin/flow) -  Flow APIs that enable flows to optimally emit state updates and emit values to multiple consumers.
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - SQLite object mapping library.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - SQLite object mapping library.
- [Mockito](http://site.mockito.org/) - Most popular mocking framework for Java/kotlin.
