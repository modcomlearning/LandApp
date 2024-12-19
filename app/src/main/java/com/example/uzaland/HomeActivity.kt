package com.example.uzaland

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
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
//        link sell land to AddLand activity
        val addland = findViewById<Button>(R.id.sellLandButton)
        addland.setOnClickListener {
            val intent = Intent(applicationContext, AddLand::class.java)
            startActivity(intent)
        }
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
                val employeeJsonArray = JSONArray(result.toString())

                //load the results to the textView
                val empdata = findViewById<TextView>(R.id.empdata)

                //a single land
                (0 until employeeJsonArray.length()).forEach {
                    val employee = employeeJsonArray.getJSONObject(it)
                    empdata.append("Land Owner:  " + employee.get("land_owner") + "\n")
                    empdata.append("Land Location: " + employee.get("land_location") + "\n")
                    empdata.append("Land Size:  " + employee.get("land_size") + "\n")
                    empdata.append("Land Cost:     " + employee.get("land_cost") + "\n")
                    empdata.append("Land Description: " + employee.get("land_description") + "\n")
                    empdata.append("\n \n")
                    empdata.append("\n \n")
                }
                //stop the progressbar after a successfull fetch of data
                progress.visibility = View.GONE




            }

        })
    }
}