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
            body.put("land_photo", "imageUrl")

            // Access the API helper class
            val helper = ApiHelper(applicationContext)

            // Trigger the POST function inside the API helper class
           // helper.post(api, body)
        }
    }
}
