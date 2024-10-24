# TV Series App

This is a TV series application built using **MVVM**, **Retrofit**, **Room**, **Navigation**, **LiveData**, and **Material3**. The app retrieves TV series data (such as photos, names, information, and ratings) from an external API. It also displays **popular series** and allows users to add series to their **Favorites** or **Watchlist** for future reference.

## Features
- **MVVM Architecture**: A clean separation of concerns using Model-View-ViewModel pattern.
- **Retrofit**: Used for API requests to fetch TV series data.
- **Room Database**: For local persistence of Favorites and Watchlist data.
- **Jetpack Navigation**: Seamless navigation between screens.
- **LiveData**: To observe data changes and update the UI reactively.
- **Material3**: Modern UI components following Material Design guidelines.
- **Popular Series**: Displays popular TV series based on current trends.
- **Rating**: Fetches and displays the rating of each series.

## Screen Recording
[Screen_recording_20241024_142457.webm](https://github.com/user-attachments/assets/6c123b94-e515-49e5-a86e-a0fa3f6be92f)

## Screenshots
![Ekran görüntüsü 2024-10-24 143903](https://github.com/user-attachments/assets/4c09ec8c-b840-4fb5-a88b-479121d3db87)
![Ekran görüntüsü 2024-10-24 143922](https://github.com/user-attachments/assets/ab1131b9-f093-4817-9d60-ed1c1892fd33)
![Ekran görüntüsü 2024-10-24 144003](https://github.com/user-attachments/assets/748ce298-dbd0-4f21-9364-a069bef1546f)

## Tech Stack
- **Kotlin**: The programming language used.
- **Jetpack Compose**: UI framework.
- **Retrofit**: To make network requests.
- **Room**: Local database for storing favorites and watchlist.
- **LiveData**: To notify UI about data changes.
- **Coroutines**: To handle asynchronous operations.
- **Navigation**: For handling in-app navigation.

## Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/Korugan32/TvSeriesApp.git
