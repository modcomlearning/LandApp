package com.example.uzaland

import android.content.Context
import android.widget.Toast
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject
class ApiHelper(var context: Context) {

    //POST
    fun post(api: String, params: RequestParams) {
        Toast.makeText(context, "Please wait for response", Toast.LENGTH_LONG).show()
        val client = AsyncHttpClient(true, 80, 443)

        // Post to API
        client.post(api, params, object : JsonHttpResponseHandler() {
            // Get response on success
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                response: JSONObject?
            ) {
                Toast.makeText(context, "Response: $response", Toast.LENGTH_SHORT).show()
            }
            // Get response on failure
            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                throwable: Throwable?,
                errorResponse: JSONObject?
            ) {
                Toast.makeText(context, "Error occurred: ${throwable?.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

//    POST for Login
    fun post_login(api: String, params: RequestParams, callBack: CallBack) {
        val client = AsyncHttpClient(true, 80, 443)

        // Get response on success
        client.post(api, params, object : JsonHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                response: JSONObject
            ) {
                callBack.onSuccess(response.toString())
            }

            // Get response on failure
            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseString: String?,
                throwable: Throwable?
            ) {
                Toast.makeText(context, "Error Occurred: $throwable", Toast.LENGTH_LONG).show()
            }
        })
    }



    //GET Records from API
    fun get(api: String, callBack: CallBack) {
        val client = AsyncHttpClient(true, 80, 443)
        //GET to API
        client.get(context, api, null, "application/json",
            object : JsonHttpResponseHandler(){
                // Get response on success
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    response: JSONArray
                ) {
                    callBack.onSuccess(response.toString())

                }
                // Get response on success
                override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseString: String?,
                    throwable: Throwable?
                ) {
                    Toast.makeText(context, "Error Occurred"+throwable.toString(), Toast.LENGTH_LONG).show()
                }

            })

    }//END GET

    //Interface to used by the GET function above.
    interface CallBack {
        fun onSuccess(result: String?)
    }

}//END CLASS