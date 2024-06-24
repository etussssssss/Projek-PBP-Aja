package com.example.projekpbpdr.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projekpbpdr.R


//AdapterRecycleView
class AdapterRecycleView (private val dataList: List<String>) : RecyclerView.Adapter<AdapterRecycleView.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val textView: TextView = itemView.findViewById(R.id.textViewMain1)
        val imageViewArray: Array<ImageView> = arrayOf(
            itemView.findViewById(R.id.imageView1),
            itemView.findViewById(R.id.imageView2),
            itemView.findViewById(R.id.imageView3),
            itemView.findViewById(R.id.imageView4)
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_viewimg, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // holder.textView.text = dataList[position]
        holder.imageViewArray.forEach { imageView ->
            imageView.setOnClickListener {
                it.animate().scaleX(0.95f).scaleY(0.95f).setDuration(100).withEndAction {
                    it.animate().scaleX(1f).scaleY(1f).setDuration(100)
                }
                val imageViewIndex = holder.adapterPosition % holder.imageViewArray.size
                Toast.makeText(holder.itemView.context, "ImageView ${imageViewIndex + 1} clicked! ${dataList[position]}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount() = dataList.size
}

//Abstract Class
abstract class EndlessRecyclerViewScrollListener(private val layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {
    private var visibleThreshold = 5
    private var loading = true
    private var previousTotalItemCount = 0
    private var startingPageIndex = 0
    private var currentPage = 0

    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        val totalItemCount = layoutManager.itemCount
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex
            this.previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                this.loading = true
            }
        }

        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
            currentPage++
            onLoadMore(currentPage, totalItemCount, view)
            loading = true
        }
    }

    abstract fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView)
}