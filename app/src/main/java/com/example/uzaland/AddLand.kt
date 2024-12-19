package com.example.uzaland

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONObject

class AddLand : AppCompatActivity() {

    // Constant for image picker request code
    private val IMAGE_PICK_CODE = 1000
    private var selectedImageUri: Uri? = null // Variable to hold the selected image URI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_land)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Fetch the EditText views
        val landDescription = findViewById<EditText>(R.id.landDescription)
        val landCost = findViewById<EditText>(R.id.landCost)
        val landLocation = findViewById<EditText>(R.id.landLocation)
        val landSize = findViewById<EditText>(R.id.landSize)
        val landOwner = findViewById<EditText>(R.id.landOwner)
        val plotNumber = findViewById<EditText>(R.id.plotNumber)

        // Fetch the Add Land Button and set OnClickListener
        val btnAddLand = findViewById<Button>(R.id.btnaddland)
        btnAddLand.setOnClickListener {
            // Specify the URL to get the Add Land API
            val api = "https://modcom2.pythonanywhere.com/api/add_land"

            // Create a JSON object to hold data gotten from the EditTexts
            val body = JSONObject()
            body.put("land_description", landDescription.text.toString())
            body.put("land_cost", landCost.text.toString())
            body.put("land_location", landLocation.text.toString())
            body.put("land_size", landSize.text.toString())
            body.put("land_owner", landOwner.text.toString())
            body.put("plot_no", plotNumber.text.toString())

            // Check if an image is selected and add the dynamic image URL
            val imageUrl = selectedImageUri?.let { uri ->
                val fileName = getFileNameFromUri(uri)
                getFullImageUrl(fileName)
            } ?: "https://labemployees.pythonanywhere.com/static/images/splashActivity.png"  // Default image if none selected

            body.put("land_photo", imageUrl)

            // Access the API helper class
            val helper = ApiHelper(applicationContext)

            // Trigger the POST function inside the API helper class
           // helper.post(api, body)
        }

        // Fetch the Upload Image Button and set OnClickListener
        val btnUploadImage = findViewById<Button>(R.id.btnUploadImage)
        btnUploadImage.setOnClickListener {
            // Intent to open file chooser for image selection
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, IMAGE_PICK_CODE)
        }
    }

    // Handle the image selection result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            selectedImageUri = data?.data
            if (selectedImageUri != null) {
                // Extract the file name from the URI
                val fileName = getFileNameFromUri(selectedImageUri!!)

                // Generate the full image URL
                val fullImageUrl = getFullImageUrl(fileName)

                // Display the selected image URL using a Toast
                Toast.makeText(this, "Selected Image URL: $fullImageUrl", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Extract the file name from the URI
    @SuppressLint("Range")
    private fun getFileNameFromUri(uri: Uri): String {
        // Use content resolver to query the file name
        val cursor = contentResolver.query(uri, arrayOf(android.provider.MediaStore.Images.Media.DISPLAY_NAME), null, null, null)
        cursor?.moveToFirst()
        val fileName = cursor?.getString(cursor.getColumnIndex(android.provider.MediaStore.Images.Media.DISPLAY_NAME))
        cursor?.close()
        return fileName ?: ""
    }

    // Generate the full image URL
    private fun getFullImageUrl(fileName: String): String {
        val baseUrl = "https://labemployees.pythonanywhere.com/static/images/"
        return baseUrl + fileName
    }
}
