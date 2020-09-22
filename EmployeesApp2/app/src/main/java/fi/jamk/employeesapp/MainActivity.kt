package fi.jamk.employeesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this)
        // Instantiate the RequestQueue
        val queue = Volley.newRequestQueue(this)
        // URL to JSON data - remember use your own data here
        val url = "https://api.jsonbin.io/b/5e889cd08841e979d0fd8e87/1"
        // Create request and listeners
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    // Get employees from JSON
                    val employees = response.getJSONArray("employees")
                    recyclerView.adapter = EmployeesAdapter(employees)
                },
                Response.ErrorListener { error ->
                    Log.d("JSON", error.toString())
                }
        )
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }
}

