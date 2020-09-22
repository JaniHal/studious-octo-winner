package fi.jamk.employeesapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.employee_item.view.*
import org.json.JSONArray
import org.json.JSONObject

class EmployeesAdapter(private val employees: JSONArray) : RecyclerView.Adapter<EmployeesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeesAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.employee_item, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.nameTextView
        val titleTextView: TextView = view.titleTextView
        val emailTextView: TextView = view.emailTextView
        val phoneTextView: TextView = view.phoneTextView
        val departmentTextView: TextView = view.departmentTextView
        val imageView: ImageView = view.imageView2

        init {
            itemView.setOnClickListener {
            val intent = Intent(view.context, EmployeeActivity::class.java)
            intent.putExtra("employee",employees[adapterPosition].toString())
            view.context.startActivity(intent)
            }
        }

    }

    override fun onBindViewHolder(holder: EmployeesAdapter.ViewHolder, position: Int) {
        // employee to bind UI
        val employee: JSONObject = employees.getJSONObject(position)
        // employee lastname and firstname
        holder.nameTextView.text =
            employee["last_name"].toString() + " " + employee["first_name"].toString()
        holder.titleTextView.text =
            employee["title"].toString()
        holder.emailTextView.text =
            employee["email"].toString()
        holder.phoneTextView.text =
            employee["phone"].toString()
        holder.departmentTextView.text =
            employee["dpeartment"].toString() //department typo in JSON
        Glide.with(holder.imageView.context).load(employee["image"].toString()).into(holder.imageView)
    }

    override fun getItemCount(): Int = employees.length()
}