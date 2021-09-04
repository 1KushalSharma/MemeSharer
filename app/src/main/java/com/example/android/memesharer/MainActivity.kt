package com.example.android.memesharer

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    val url = "https://meme-api.herokuapp.com/gimme"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getMeme()
    }

    /* private  fun loadMeme()
    {

        val queue = Volley.newRequestQueue(this)

        val JSONObjectRequest= JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val url1 = response.getString("url")
              Glide.with(this).load(url1).into(MyimageView)
                Toast.makeText(this,"Hello",Toast.LENGTH_LONG).show()

            }
        ) {
            Toast.makeText(this, "Bye", Toast.LENGTH_LONG).show()

        }

// Add the request to the RequestQueue.
        queue.add(JSONObjectRequest)
    } */
    private fun getMeme() {
        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response: JSONObject ->
                try {
                    val imageUrl = response.getString("url")
                    Glide.with(this@MainActivity).load(imageUrl)
                        .into(myimageview)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        ) { error: VolleyError? ->
            Toast.makeText(
                this@MainActivity,
                " Error Is Going On",
                Toast.LENGTH_SHORT
            ).show()
        }
        queue.add(jsonObjectRequest)
    }
    fun nextMeme(view: View) {
        getMeme()
    }
    fun share(view: View) {}
}