## News App
- A web-based platform designed to keep users updated with the latest news from various sources, focusing on technology and development updates.

## Features:
- News Aggregation: Fetches and displays news from multiple sources.
- Offline Support: Enables access to previously fetched news without an internet connection.
- Modern UI: A responsive interface powered by Data Binding for real-time updates.

## Technologies:
- MVVM (Model-View-ViewModel): Architectural pattern for separating UI from business logic.
- Room: Local database for offline data storage and retrieval.
- Retrofit: HTTP client for network operations and API calls.
- Coroutines: For asynchronous operations and improving performance.
- Data Binding: Connects UI components with data sources to auto-update the UI.
- Clean Architecture: Separation of concerns by dividing the code into layers (Presentation, Domain, Data).
- Hilt: Dependency injection framework for managing dependencies efficiently.

## Architecture:
The application follows Clean Architecture principles, dividing the codebase into distinct layers:
- Presentation Layer: Contains UI-related logic with ViewModel and Data Binding.
- Domain Layer: Contains business logic and use cases.
- Data Layer: Handles data sources, integrating local (Room) and remote (Retrofit) data.

## Screenshots:

<table>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/60aaafa0-40b3-4e4b-a6a5-3c59c281ed77" width="300"></td>
    <td><img src="https://github.com/user-attachments/assets/1ee9e1db-080b-4580-ab9e-8e91ae421e82" width="300"></td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/4f13f2c0-78e1-4231-bd7a-a92231a39411" width="300"></td>
    <td><img src="https://github.com/user-attachments/assets/9ce6f124-8df4-4d03-81f0-237a392af8de" width="300"></td>
  </tr>
  <tr>
   <td><img src="https://github.com/user-attachments/assets/40070913-ad29-46b2-8e5d-e3377dfd9c42" width="300"></td>
   <td><img src="https://github.com/user-attachments/assets/53970169-b6e5-405b-ac06-5f09eb81a856" width="300"></td>
  </tr>
</table>


