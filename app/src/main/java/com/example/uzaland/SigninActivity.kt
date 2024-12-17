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
        //        link to signin activity
        val gotosignup = findViewById<TextView>(R.id.linktoregister)
        gotosignup.setOnClickListener {
            val intent = Intent(application, MainActivity::class.java)
            startActivity(intent)
        }
//        fetch login button and listen on click
        val loginbtn = findViewById<Button>(R.id.login)
        loginbtn.setOnClickListener {
//            fetch the 2 inputs
            val email = findViewById<EditText>(R.id.email)
            val password = findViewById<EditText>(R.id.password)

            //Specify the /signin" Endpoint
//            val api = "https://modcom2.pythonanywhere.com/api/signin"
            val api = "https://modcom2.pythonanywhere.com/api/signin"
            val helper = ApiHelper(applicationContext)
            //Create a JSON Object of email and Password
            val body = JSONObject()
            //Use Email Edit Text
            body.put("email", email.text.toString())
            body.put("password", password.text.toString())



            helper.post_login(api, body, object : ApiHelper.CallBack{
                override fun onSuccess(result: String?) {
                    val resultObj = JSONObject(result.toString())
                    if(resultObj!!.has("user")){
                        Toast.makeText(applicationContext, "Successful", Toast.LENGTH_SHORT).show()
                        val intentHome = Intent(applicationContext, HomeActivity::class.java)
                        startActivity(intentHome)
                    }

                    else{
                        Toast.makeText(applicationContext, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                    }

                }
            })

        }
    }
}