package com.musaguzel.acicik.ui.siparislerEkrani

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.musaguzel.acicik.R
import com.musaguzel.acicik.adapters.addedOrdersAdapter
import com.musaguzel.acicik.model.addedOrdersPosts
import com.musaguzel.acicik.ui.cigkofteMenu.HomeViewModel
import com.musaguzel.acicik.ui.login.OtpFragmentArgs
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_orders.*
import kotlin.properties.Delegates


class OrdersFragment : Fragment() {

    private lateinit var viewModel: OrdersViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var addedProductName: String
    private lateinit var addedProductPrice: String
    private var addedProductNumber by Delegates.notNull<Int>()
    private lateinit var addedProductList: ArrayList<addedOrdersPosts>
    private var postAdapter = addedOrdersAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_orders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(OrdersViewModel::class.java)
        val sharedPreferences = context?.getSharedPreferences("com.musaguzel.acicik", Context.MODE_PRIVATE)

        recyclerview_orders.layoutManager = LinearLayoutManager(context)
        recyclerview_orders.adapter = postAdapter



        addedProductList = arrayListOf()

        /*arguments?.let {
            addedProductName = OrdersFragmentArgs.fromBundle(it).name
            addedProductPrice = OrdersFragmentArgs.fromBundle(it).price
            addedProductNumber = OrdersFragmentArgs.fromBundle(it).number

            if (addedProductName.equals("name") || addedProductPrice.equals("price")){ //Default deger geliyorsa
                postAdapter.updateOrdersList(OrdersViewModel.incomingData.data)
            }else if (OrdersViewModel.incomingData.data.contains(addedOrdersPosts(addedProductName,addedProductPrice,addedProductNumber))){
                println("elseif")   //Gelen data daki veri zaten varsa
                OrdersViewModel.incomingData.data.forEach {

                    if (it.productName == addedProductName){  //Dizideki tıklanan ürünü bul

                        OrdersViewModel.incomingData.data.remove(it)
                        println(OrdersViewModel.incomingData.data)
                        OrdersViewModel.incomingData.data.add(addedOrdersPosts(addedProductName,addedProductPrice,addedProductNumber+1))
                        postAdapter.updateOrdersList(OrdersViewModel.incomingData.data)
                    }
                }
            }
            else{
                //println("else")
                println(OrdersViewModel.incomingData.data)
                OrdersViewModel.incomingData.data.add(addedOrdersPosts(addedProductName,addedProductPrice,addedProductNumber))
                postAdapter.updateOrdersList(OrdersViewModel.incomingData.data)
            }
        }*/
    }

// Do List
    //aynı isimle benzer bir ürün arraylistte varsa o ürünün ya direkt adedi arttırılacak ya da listeden kaldırılıp aynı ürün
    //adedi arttırılmış bir şekilde yeniden listeye aktarılacak.
}