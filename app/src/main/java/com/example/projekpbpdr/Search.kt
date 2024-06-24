package com.example.projekpbpdr

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.projekpbpdr.adapter.MyPagerAdapter
import com.example.projekpbpdr.adapter.SearchAdapter
import com.example.projekpbpdr.adapter.animasi1
import com.example.projekpbpdr.data.SearchModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject


class Search : Fragment() {

    private lateinit var searchView: androidx.appcompat.widget.SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchAdapter: SearchAdapter

    private lateinit var viewPager: ViewPager2
    private lateinit var adapter1: MyPagerAdapter
    private val handler = Handler(Looper.getMainLooper())

    private val db = FirebaseFirestore.getInstance()


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.search, container, false)

        searchView = view.findViewById(R.id.searchView)
        searchView.queryHint = "Search..."
        recyclerView = view.findViewById(R.id.recyclerView)
        searchAdapter = SearchAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = searchAdapter

        viewPager = view.findViewById(R.id.imageView12)
        adapter1 = MyPagerAdapter(listOf(R.drawable.dekorasi1, R.drawable.dekorasi2, R.drawable.dekorasi1))
        viewPager.adapter = adapter1
        viewPositionSlide(viewPager)

        allVoid()

        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                recyclerView.visibility = View.VISIBLE
                searchAdapter.filterList(newText ?: "")
                return true
            }
        })

        searchView.setOnClickListener {
            searchView.isIconified = false
            searchView.requestFocus()
            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(searchView.findFocus(), InputMethodManager.SHOW_IMPLICIT)
            Toast.makeText(requireContext(), " icon clicked", Toast.LENGTH_SHORT).show()
        }

        searchView.setOnSearchClickListener {
            Toast.makeText(requireContext(), "Search icon clicked", Toast.LENGTH_SHORT).show()
        }


        searchView.setOnCloseListener {
            if (!searchView.query.isNullOrEmpty()) {
                searchView.setQuery("", false)
            }

            Toast.makeText(requireContext(), "Close icon clicked", Toast.LENGTH_SHORT).show()
            recyclerView.visibility = View.GONE
            recyclerView.clearFocus()
            false
        }


        return view
    }

    fun allVoid(){
        val autoSlideRunnable = object : Runnable {
            override fun run() {
                val currentItem = viewPager.currentItem
                viewPager.currentItem = (currentItem + 1) % adapter1.itemCount
                handler.postDelayed(this, 3000L)
            }
        }

        handler.postDelayed(autoSlideRunnable, 2000L)
        loadDataFromFirebase()
    }

    fun viewPositionSlide(viewPage: ViewPager2){
        viewPage.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            val autoSlideRunnable = object : Runnable {
                override fun run() {
                    val currentItem = viewPager.currentItem
                    viewPager.currentItem = (currentItem + 1) % adapter1.itemCount
                    handler.postDelayed(this, 3000L)
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    animasi1(viewPage)
                    handler.postDelayed(autoSlideRunnable, 3000L)
                }else{
                    animasi1(viewPage)
                    handler.removeCallbacks(autoSlideRunnable)
                }
            }
        })
    }




    private fun loadDataFromFirebase() {
        db.collection("user").get().addOnSuccessListener { documents ->
                val items = mutableListOf<SearchModel>()
                for (document in documents) {
                    val item = document.toObject<SearchModel>()
                    items.add(item)
                }
                searchAdapter.setAllItems(items)
            }.addOnFailureListener { exception ->

            }
    }
}