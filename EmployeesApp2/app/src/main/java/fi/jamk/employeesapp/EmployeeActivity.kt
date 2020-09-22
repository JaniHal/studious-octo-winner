package fi.jamk.employeesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_employee.*
import org.json.JSONObject

class EmployeeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee)


        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            val employeeString = bundle!!.getString("employee")
            val employee = JSONObject(employeeString)
            val name = employee["last_name"].toString() + " " + employee["first_name"].toString()
            val title = employee["title"].toString()
            val email = employee["email"].toString()
            val phone = employee["phone"].toString()
            val department = employee["dpeartment"].toString() //department typo in json
            val image = employee["image"].toString()
            nameTextView.text = name
            titleTextView.text=title
            emailTextView.text=email
            phoneTextView.text=phone
            departmentTextView.text=department
            Glide.with(imageView.context).load(employee["image"].toString()).into(imageView)
        }
    }
}
