package com.musaguzel.acicik.ui.siparislerEkrani

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.musaguzel.acicik.BaseViewModel
import com.musaguzel.acicik.model.Posts
import com.musaguzel.acicik.model.addedOrdersPosts

class OrdersViewModel(application: Application) : BaseViewModel(application) {


     var nullIncomingData = MutableLiveData<Boolean>()

    private val context = getApplication<Application>().applicationContext

    val sharedPreferences =
            context.getSharedPreferences("com.musaguzel.acicik", Context.MODE_PRIVATE)



 object incomingData{
     val data: ArrayList<addedOrdersPosts> = arrayListOf()
 }
}