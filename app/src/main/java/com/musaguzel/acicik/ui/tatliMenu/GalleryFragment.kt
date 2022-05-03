package com.musaguzel.acicik.ui.tatliMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.musaguzel.acicik.R
import com.musaguzel.acicik.model.Posts
import com.musaguzel.acicik.adapters.RecyclerAdapter
import kotlinx.android.synthetic.main.fragment_gallery.*

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel
    private var postAdapter = RecyclerAdapter(arrayListOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        galleryViewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)
        galleryViewModel.refreshData()

        recyclerView_tatli.layoutManager = LinearLayoutManager(context)
        recyclerView_tatli.adapter = postAdapter

        observeLiveData()
    }

    fun observeLiveData(){
        galleryViewModel.posts.observe(viewLifecycleOwner, Observer { posts ->
            posts?.let {
                postAdapter.updatePostList(posts as ArrayList<Posts>)
            }
        })
    }
}