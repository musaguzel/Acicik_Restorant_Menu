package com.musaguzel.acicik.ui.cigkofteMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.musaguzel.acicik.R
import com.musaguzel.acicik.adapters.RecyclerAdapter
import com.musaguzel.acicik.model.Posts
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var postAdapter = RecyclerAdapter(arrayListOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.refreshData()

        //Recycler view bağlama
        recyclerView_cigkofte.layoutManager = LinearLayoutManager(context)
        recyclerView_cigkofte.adapter = postAdapter

        observeLiveData()
    }

    fun observeLiveData(){
        homeViewModel.posts.observe(viewLifecycleOwner, Observer { posts ->
            posts?.let {
                recyclerView_cigkofte.visibility = View.VISIBLE
                println(posts[0].imageUrl)
                postAdapter.updatePostList(posts as ArrayList<Posts>)
            }
        })
    }
}