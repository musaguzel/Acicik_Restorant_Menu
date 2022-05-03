package com.musaguzel.acicik.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.musaguzel.acicik.R
import com.musaguzel.acicik.model.Posts
import com.musaguzel.acicik.model.addedOrdersPosts
import com.musaguzel.acicik.ui.cigkofteMenu.HomeFragmentDirections
import com.musaguzel.acicik.ui.siparislerEkrani.OrdersViewModel
import com.musaguzel.acicik.util.getImageFromFirebase
import com.musaguzel.acicik.util.placeholderShimmer
import kotlinx.android.synthetic.main.recycler_orders.view.*
import kotlinx.android.synthetic.main.recycler_posts.view.*

class RecyclerAdapter(val urunList: ArrayList<Posts>): RecyclerView.Adapter<RecyclerAdapter.PostViewHolder>() {

    class PostViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_posts, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        val sharedPreferences =
                holder.view.context.getSharedPreferences("com.musaguzel.acicik", Context.MODE_PRIVATE)


        holder.view.productImage.getImageFromFirebase(urunList[position].imageUrl,
            placeholderShimmer(holder.view.context))


        holder.view.productName.text = urunList[position].productName
        holder.view.productPrice.text = urunList[position].productPrice



       /* holder.view.txtAddToBox.setOnClickListener {
            val action = HomeFragmentDirections.actionNavHomeToOrdersFragment(urunList[position].productName
                    ,urunList[position].productPrice,1)
            Navigation.findNavController(it).navigate(action)
        }*/


    }

    override fun getItemCount(): Int {
        return urunList.size
    }

    fun updatePostList(newPostList: ArrayList<Posts>){
        urunList.clear()
        urunList.addAll(newPostList)
        notifyDataSetChanged()
    }
}