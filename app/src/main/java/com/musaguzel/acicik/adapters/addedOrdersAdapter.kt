package com.musaguzel.acicik.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.musaguzel.acicik.R
import com.musaguzel.acicik.model.addedOrdersPosts
import kotlinx.android.synthetic.main.recycler_orders.view.*

class addedOrdersAdapter(val eklenenUrunler: ArrayList<addedOrdersPosts>): RecyclerView.Adapter<addedOrdersAdapter.PostViewHolder>() {

    class PostViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): addedOrdersAdapter.PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_orders, parent, false)
        return PostViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: addedOrdersAdapter.PostViewHolder, position: Int) {
        holder.view.ordersScrProductName.text = eklenenUrunler[position].productName
        holder.view.ordersScrProductPrice.text = eklenenUrunler[position].productPrice
        holder.view.ordersScrProductNumber.text = eklenenUrunler[position].productNumber.toString() + " Adet"
    }

    override fun getItemCount(): Int {
        return eklenenUrunler.size
    }

    fun updateOrdersList(newPostList: ArrayList<addedOrdersPosts>){
        eklenenUrunler.clear()
        eklenenUrunler.addAll(newPostList)
        notifyDataSetChanged()
    }

  /*  fun updateNumberOfProducts(productName: String){
        eklenenUrunler.forEach {
            if (it.productName == productName){

            }
        }
    }*/
}