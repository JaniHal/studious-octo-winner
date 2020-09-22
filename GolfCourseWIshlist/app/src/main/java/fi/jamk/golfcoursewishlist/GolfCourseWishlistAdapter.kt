package fi.jamk.golfcoursewishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_places.view.*


class GolfCourseWishlistAdapter(private val places: ArrayList<Place>)
    : RecyclerView.Adapter<GolfCourseWishlistAdapter.ViewHolder>(){

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.placeName
        val imageView: ImageView = view.placeImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GolfCourseWishlistAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.row_places, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int=places.size


    override fun onBindViewHolder(holder: GolfCourseWishlistAdapter.ViewHolder, position: Int) {
        val place: Place = places.get(position)
        holder.nameTextView.text = place.name
        Glide.with(holder.imageView.context).load(place.getImageResourceId(holder.imageView.context)).into(holder.imageView)
        }
}
