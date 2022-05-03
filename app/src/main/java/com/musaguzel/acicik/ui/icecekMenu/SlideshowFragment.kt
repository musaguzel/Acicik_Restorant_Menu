package com.musaguzel.acicik.ui.icecekMenu

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
import kotlinx.android.synthetic.main.fragment_slideshow.*

class SlideshowFragment : Fragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel
    private var postAdapter = RecyclerAdapter(arrayListOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        slideshowViewModel = ViewModelProvider(this).get(SlideshowViewModel::class.java)
        slideshowViewModel.refreshData()

        //Recycler view bağlama
        recyclerView_icecekler.layoutManager = LinearLayoutManager(context)
        recyclerView_icecekler.adapter = postAdapter

        observeLiveData()
    }

    fun observeLiveData(){
        slideshowViewModel.posts.observe(viewLifecycleOwner, Observer { posts ->
            posts?.let {
                postAdapter.updatePostList(posts as ArrayList<Posts>)
            }
        })
    }
}