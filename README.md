# LandApp - Land Management System

LandApp is an Android application designed for managing land listings. Users can sign up, log in, and view all posted lands through an intuitive interface. The app communicates with a backend API, allowing developers to easily extend and integrate with their systems.

This project uses Android Studio, and the API for this app is available at this Github Link: [modcomlearning/BackendAPI](https://github.com/modcomlearning/BackendAPI).

## Features

- **Login**: Securely log in with registered credentials.
- **Sign Up**: New users can sign up with their details and become part of the system.
- **View Lands**: Users can view all the posted land details including owner, location, size, cost, and description.
- **API Integration**: The app integrates with a backend API to retrieve data about lands.

## Prerequisites

To set up the project, you will need the following:
- **Android Studio** (any version supporting Kotlin).
- A basic understanding of Android development.
- Access to the **modcomlearning Backend API** (API documentation and setup instructions can be found [here](https://github.com/modcomlearning/BackendAPI)).

## Step 1: Setting Up the Android Studio Project

1. **Create a new Android Studio project** named `LandApp`.
    - Select **Empty Activity** when prompted.
    - Make sure to choose **Kotlin** as the programming language.

2. **Add Dependencies**:
    - Open the `build.gradle` file (usually found in the `app` folder).
    - In the `dependencies` section, add the following dependencies:

   ```gradle
   dependencies {
       // LoopJ for API Connections
       implementation ("com.loopj.android:android-async-http:1.4.9")
   
       // For JSON Conversions, From JSONArray to ArrayList
       implementation ("com.google.code.gson:gson:2.8.7") 
   }

