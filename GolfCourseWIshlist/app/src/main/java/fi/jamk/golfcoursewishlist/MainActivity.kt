package fi.jamk.golfcoursewishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


private var isListView = true
private var mStaggeredLayoutManager : StaggeredGridLayoutManager? = null

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mStaggeredLayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = mStaggeredLayoutManager
        recyclerView.adapter = GolfCourseWishlistAdapter(Places.placeList())
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_toggle -> {
                if (isListView) {
                    item.setIcon(R.drawable.ic_view_stream_white_24dp)
                    item.setTitle("Show as list")
                    isListView = false
                    mStaggeredLayoutManager?.setSpanCount(2)
                } else {
                    item.setIcon(R.drawable.ic_view_column_white_24dp)
                    item.setTitle("Show as grid")
                    isListView = true
                    mStaggeredLayoutManager?.setSpanCount(1)
                }
                true
            }
            else ->super.onOptionsItemSelected(item)
                }
            }
}

