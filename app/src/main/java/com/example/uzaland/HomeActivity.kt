package com.example.uzaland

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONArray

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//
        //find the progressbar by use of its id
        val progress = findViewById<ProgressBar>(R.id.progress)

        //specify the url to get the lands
        val api = "https://modcom2.pythonanywhere.com/api/get_land_details"

        //access the helper
        val helper = ApiHelper(applicationContext)

        //inside of the helper class, access the get function
        helper.get(api, object : ApiHelper.CallBack {
            //convert the get results into a json array
            override fun onSuccess(result: String?) {
                val landJsonArray = JSONArray(result.toString())

                //load the results to the textView
                val landdata = findViewById<TextView>(R.id.landdata)

                //a single land
                (0 until landJsonArray.length()).forEach {
                    val land = landJsonArray.getJSONObject(it)
                    landdata.append("Land Owner:  " + land.get("land_owner") + "\n")
                    landdata.append("Land Location: " + land.get("land_location") + "\n")
                    landdata.append("Land Size:  " + land.get("land_size") + "\n")
                    landdata.append("Land Cost:     " + land.get("land_cost") + "\n")
                    landdata.append("Land Description: " + land.get("land_description") + "\n")
                    landdata.append("\n \n")
                }
                //stop the progressbar after a successfull fetch of data
                progress.visibility = View.GONE
            }

        })
    }
}