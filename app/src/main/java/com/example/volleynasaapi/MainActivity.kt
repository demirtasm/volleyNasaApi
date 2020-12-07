package com.example.volleynasaapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var queue: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,
                NasaUrl().url +"api_key="+ NasaUrl().apiKey, null, { response ->
            var title = response?.getString("title")
            var date = response?.getString("date")
            var imgPoster = response?.getString("url")
            tvTitle.text = title
            tvDate.text = date
            Picasso.get().load(imgPoster).into(imgURL)
        }, {
            Toast.makeText(this, "Someting went wrong!", Toast.LENGTH_LONG).show()

        })

        queue?.add(jsonObjectRequest)
    }
}