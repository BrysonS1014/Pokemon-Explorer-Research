
# Pokémon Explorer

## Overview

Pokémon Explorer is a sample Android application created to research technologies that could potentially be used in my Capstone project, TrailMate. The application allows users to search 
for Pokémon and retrieve information about them, including their names, IDs, heights, types, and sprites. It primarily demonstrates the use of Retrofit for retrieving data from an external 
API and Coil for displaying images.

## Technologies Used

### Primary Research Technologies
- **Retrofit:** Makes HTTP requests to retrieve Pokémon information
- **Coil:** Downloads and displays Pokémon sprites

### Supporting Technologies
- **Gson Converter:** Converts JSON responses into Kotlin objects
- **Kotlin Coroutines:** Handles asynchronous API requests
- **Kotlin:** Programming language.
- **Jetpack Compose:** Android UI framework.

### Demonstration API
- **PokéAPI:** A public REST API used as a sample data source for this research application. PokéAPI provides Pokémon information and sprite URLs, allowing the application to
  demonstrate Retrofit's HTTP networking capabilities, Gson's JSON deserialization, and Coil's image-loading functionality. PokéAPI is not intended to be integrated into TrailMate; it was
  selected to provide a convenient environment for evaluating these technologies. 
## Features

- Search for Pokémon
- Selected Pokémon's sprite is displayed
- Selected Pokémon's name is displayed
- Selected Pokémon's id, height(in meters), and type(s) are displayed

## How to Run

1. Clone this repository.
2. Open the project in Android Studio.
3. Allow Gradle to synchronize dependencies.
4. Select an Android emulator or connected device with at least Android 8.
5. Run the application.

## Research Findings

### Retrofit
Retrofit makes it straightforward to retrieve JSON data from an API and convert it into Kotlin objects using Gson Converter. In this application, Retrofit uses the Pokémon name entered by 
the user to construct an HTTP request to PokéAPI, which then returns the selected Pokémon's information.

### Coil
Coil makes it straightforward to load and display images from URLs in a Jetpack Compose application. In Pokémon Explorer, the Pokémon data includes a sprite URL 
stored in the `PokemonSprites` data class. Once the URL is retrieved from PokéAPI, Coil uses it to load and display the selected Pokémon's sprite.

### Challenges
A lot of challenges came from the fact I had never used Kotlin or Jetpack Compose. I had to quickly learn the syntax for Kotlin while also implementing familiar concepts regarding states 
and then learning how Column, Modifier, and Card work together. When it came to troubleshooting, I had some issues with Gradle dependencies with having the wrong module identifiers and had some 
issues resolving certain dependencies. Lastly, I had some issues converting JSON into Kotlin objects, however most of it was just formatting and adding @SerializedName annotations because Kotlin uses
camelCase. Because of these challenges I learned:
- How to build an interactive Android interface using Kotlin and Jetpack Compose, including user input, conditional rendering, and state management.
- How Gradle version catalogs manage libraries and versions, and how dependency configurations affect whether libraries can be resolved.
- How JSON deserialization works, how to represent nested objects and lists, and why JSON property names must be mapped correctly.

## Potential Applications to TrailMate

- Retrofit: Could integrate external APIs for trails, weather, or other relevant information
- Coil: Could display creature artwork, Trail Cards, and other images
- Gson-Converter: Could process data returned by external APIs
- Kotlin Coroutines: Useful for networking and other asynchronous operations

## Screenshots
<img width="288" height="505" alt="Screenshot from 2026-10-11 21-25-37" src="https://github.com/user-attachments/assets/40d879f3-4430-4749-ad60-0598dc55a68b" />

