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
        android:id="@+id/linktosignin" />
</LinearLayout>
```


## Step 3: Implement Signup Logic in MainActivity.kt

Now, implement the logic for collecting user data and sending it to the API when the Create Account button is clicked. The following code will handle the signup process.

In MainActivity.kt add below code
```kotlin
package com.example.landapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
       
        // Fetch the input fields
        val username = findViewById<EditText>(R.id.username)
        val email = findViewById<EditText>(R.id.email)
        val phone = findViewById<EditText>(R.id.phone)
        val password = findViewById<EditText>(R.id.password)

        // Fetch the Create Account button and set the OnClickListener
        val btnregister = findViewById<Button>(R.id.create)
        btnregister.setOnClickListener {
            // Prepare the data to send to the backend API
            val api = "https://modcom2.pythonanywhere.com/api/signup"  // Replace this with your API endpoint
            val helper = ApiHelper(applicationContext)  // Assuming ApiHelper is implemented to handle requests
            val body = JSONObject()
            body.put("username", username.text.toString())
            body.put("email", email.text.toString())
            body.put("phone", phone.text.toString())
            body.put("password", password.text.toString())

            // Post the data to the API
            helper.post(api, body)
        }
    }
}

```

## Code Explanation:

### 1. **UI Setup**:
- `setContentView(R.layout.activity_main)`: Sets the content view to the layout defined in `activity_main.xml`.
- `ViewCompat.setOnApplyWindowInsetsListener`: Adjusts UI padding to prevent overlap with the system UI (status bar and navigation bar).

### 2. **Navigation to Login Screen**:
- The **TextView** `linktologin` allows users to navigate to the `SigninActivity` if they already have an account.

### 3. **Fetching User Input**:
- `EditText` fields for **username**, **email**, **phone**, and **password** are linked to variables to get the data entered by the user.

### 4. **Handling Button Click**:
- When the **Create Account** button is clicked, the entered data is collected and sent as a **POST** request to the API.

### 5. **Posting Data to the API**:
- The `helper.post(api, body)` sends the data to the backend API for account creation. The `ApiHelper` class should manage the actual HTTP request.




## Step 4: Adding a Sign-In Functionality

In this step, we will create a **Sign-In** functionality, allowing users to log in to the app. We'll create a new `SigninActivity` with its layout and logic to handle user authentication.

### 1. **Create a New Activity for Sign-In**:
- Open **Android Studio** and create a new activity named **SigninActivity**. When prompted, choose the **Empty Views Activity** option.

#### **activity_signin.xml**:
In `res/layout/activity_signin.xml`, add the following code to design the sign-in screen:

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:layout_margin="10dp"
    tools:context=".SigninActivity">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Login Account"
        android:textStyle="bold"
        android:textSize="30sp"
        android:textColor="@color/black" />

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
        android:hint="Your Password"
        android:inputType="textPassword"
        android:id="@+id/password" />

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Login Account"
        android:id="@+id/login"
        android:layout_marginTop="10dp" />
   
</LinearLayout>
```

## Step 4: Handle User Login in SigninActivity.kt:

Now, open SigninActivity.kt and add the following code to handle the login logic:

```kotlin
package com.example.uzaland

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONObject

class SigninActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
       

        // Fetch Login Button and listen for clicks
        val loginbtn = findViewById<Button>(R.id.login)
        loginbtn.setOnClickListener {
            // Fetch the email and password inputs
            val email = findViewById<EditText>(R.id.email)
            val password = findViewById<EditText>(R.id.password)

            // Specify the /signin endpoint
            val api = "https://modcom2.pythonanywhere.com/api/signin" // Replace with your API endpoint
            val helper = ApiHelper(applicationContext)

            // Create a JSON Object with email and password
            val body = JSONObject()
            body.put("email", email.text.toString())
            body.put("password", password.text.toString())

            // Make the POST request to sign in
            helper.post_login(api, body, object : ApiHelper.CallBack {
                override fun onSuccess(result: String?) {
                    val resultObj = JSONObject(result.toString())
                    if(resultObj!!.has("user")) {
                        Toast.makeText(applicationContext, "Successful", Toast.LENGTH_SHORT).show()
                        // Navigate to Home Activity on successful login
                        val intentHome = Intent(applicationContext, HomeActivity::class.java)
                        startActivity(intentHome)
                    } else {
                        Toast.makeText(applicationContext, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }
}

```
### Link Sign-Up from MainActivity:
To allow users to navigate from the sign-un screen to the sign-in screen, update the MainActivity with the following code:

```kotlin
         //link to signin activity
        val gotosignin = findViewById<TextView>(R.id.linktosignin)
        gotosignin.setOnClickListener {
            val intent = Intent(application, SigninActivity::class.java)
            startActivity(intent)
        }
```


## Code Explanation:
### UI Setup:
- `setContentView(R.layout.activity_signin)` loads the layout for the sign-in screen.
- `ViewCompat.setOnApplyWindowInsetsListener` adjusts padding for system UI (e.g., status bar).

### Linking to Sign-up:
- The **TextView** `linktoregister` provides a navigation link to the **MainActivity** for signing up.

### Handling Login:
- The user inputs their **email** and **password** in `EditText` fields.
- A **JSON Object** is created with the user's input and sent to the `/signin` endpoint.
- If the response contains a user, the login is successful, and the user is redirected to the **HomeActivity**.
- If the credentials are invalid, a **Toast** message displays an error.



