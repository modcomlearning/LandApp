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
import org.json.JSONArray
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
//        link to signin activity
        val gotosignin = findViewById<TextView>(R.id.linktologin)
        gotosignin.setOnClickListener {
            val intent = Intent(application, SigninActivity::class.java)
            startActivity(intent)
        }

//        fetch the 4 inputs
        val username = findViewById<EditText>(R.id.username)
        val email = findViewById<EditText>(R.id.email)
        val phone = findViewById<EditText>(R.id.phone)
        val password = findViewById<EditText>(R.id.password)

//        fetch create button and listen for the click
        val btnregister  = findViewById<Button>(R.id.create)
        btnregister.setOnClickListener {
            //Post data to /signup API
            val api = "https://modcom2.pythonanywhere.com/api/signup"
            val helper = ApiHelper(applicationContext)
            val body = JSONObject()
            body.put("username", username.text.toString())
            body.put("email", email.text.toString())
            body.put("phone", phone.text.toString())
            body.put("password", password.text.toString())
//            helper.post(api,body)

            helper.post(api, body)
        }
    }
}