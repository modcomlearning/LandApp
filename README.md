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
    - Select **Empty Views Activity** when prompted.
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



Next go to https://justpaste.it/99stf
Copy the ApiHelper Code and Paste it in your app Package.
The ApiHelper will be used to send requests to and from our API Using Loopj HTTP Library



## Step 2: Implementing Signup Functionality

In this step, we will create the signup functionality in LandApp. This allows users to register by submitting their details to the backend API.

---

### 1. **Create Layout for Signup (`activity_main.xml`)**

In the `activity_main.xml`, we will create the layout that includes fields for **Username**, **Email**, **Phone**, and **Password**, along with a button to submit the form.

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:layout_margin="10dp"
    tools:context=".MainActivity">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Create an Account"
        android:textStyle="bold"
        android:textSize="30sp"
        android:textColor="@color/black" />

    <EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        android:hint="Your Username"
        android:inputType="textPersonName"
        android:id="@+id/username" />

    <EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        android:hint="Your Email"
        android:inputType="textEmailAddress"
        android:id="@+id/email" />

    <EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        android:hint="Your Phone"
        android:inputType="phone"
        android:id="@+id/phone" />

    <EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        android:hint="Your Password"
        android:inputType="textPassword"
        android:id="@+id/password" />

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Create Account"
        android:id="@+id/create"
        android:layout_marginTop="10dp" />

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Already have an Account, Login"
        android:padding="20dp"
        android:textStyle="bold"
        android:textColor="#3F51B5"
        android:id="@+id/linktologin" />
</LinearLayout>















