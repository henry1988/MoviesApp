# Movie App

This is a simple movie app built with Compose Multiplatform that allows users to browse and discover movies.

## Features

* **Browse Popular Movies:** View a list of currently popular movies.
* **Explore New Releases:** Discover the latest movies that have been released.
* **Manage My List:** Create and maintain apersonal list of favorite movies.
* **View Movie Details:** Access detailed information about a movie, including:
  * Name
  * Poster
  * Genres
  * Overview
  * Popularity
  * Release Date
  * Languages
  * Vote Average

## Technologies Used

* **Compose Multiplatform:** UI framework for building cross-platform applications.
* **Kamel:** Image loading library for Compose Multiplatform.
* **Kotlin Coroutines:** For asynchronous operations and managing background tasks.
* **StateFlow:** For observing and reacting to changes in the UI state.
* **ViewModel:** Formanaging UI-related data and logic.
* **Navigation Compose:** For navigating between different screens in the app.

## Architecture

The app follows a clean architecture approach, separating concerns into different layers:

* **UI Layer:** Compose composables responsible for rendering the user interface.
* **ViewModel Layer:** ViewModels that hold UI state and interact with use cases.
* **Use Case Layer:** Defines the business logic of the app, such as fetching movie data.
* **Data Layer:** Responsible for interacting with data sources, such as a remote API or local database.

## How to Run

1. Clone the repository.
2. Open the project in your IDE.
3. Build and run the app on your desired platform (Android, iOS, Desktop).
4. Add your API key to the MoviesComponent file

## Future Enhancements

* **Search Functionality:** Allow users to search for movies by title or keywords.
* **User Authentication:** Implement user login and registration to enable personalized lists and recommendations.
* **Offline Support:** Cache movie data locally to allow browsing even without an internet connection.
* **Trailer Playback:** Integrate video playback to allow users to watch movie trailers.

Feel free to contribute to the project by submitting pull requests or reporting issues!