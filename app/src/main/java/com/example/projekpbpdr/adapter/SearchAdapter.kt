package com.example.projekpbpdr.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.projekpbpdr.R
import com.example.projekpbpdr.data.SearchModel
import kotlin.math.abs


class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private var items: List<SearchModel> = listOf()
    private var allItems: List<SearchModel> = listOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.nameTextView.text = item.nama
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setAllItems(allItems: List<SearchModel>) {
        this.allItems = allItems
        filterList("")
    }

    fun filterList(query: String) {
        items = if (query.isEmpty()) {
            listOf()
        } else {
            allItems.filter {
                it.nama.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }
}

class SlideAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment = ScreenSlidePageFragment()
}


class ScreenSlidePageFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View = inflater.inflate(R.layout.search_slide_page, container, false)
}

class MyPagerAdapter(private val images: List<Int>) : RecyclerView.Adapter<MyPagerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_slide_page, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageResource(images[position])
    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView9)
    }
}

fun animasi1(viewPager: ViewPager2){
    viewPager.setPageTransformer { page: View, position: Float ->
        page.setAlpha(
            (1 - abs(position.toDouble())).toFloat()
        )
    }
}


//class SearchAdapter(private var items: List<SearchModel>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
//
//    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val nama: TextView = itemView.findViewById(R.id.textView)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_layout, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = items[position]
//        holder.nama.text = item.nama
//    }
//
//    override fun getItemCount(): Int {
//        return items.size
//    }
//
//    fun filterList(filteredItems: List<SearchModel>) {
//        items = filteredItems
//        notifyDataSetChanged()
//    }
//
//
//}


