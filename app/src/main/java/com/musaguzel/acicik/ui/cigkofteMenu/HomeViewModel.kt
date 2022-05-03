package com.musaguzel.acicik.ui.cigkofteMenu

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.musaguzel.acicik.BaseViewModel
import com.musaguzel.acicik.model.Posts
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : BaseViewModel(application) {


    val firebaseFirestore = Firebase.firestore
    val auth = Firebase.auth

    val postLoading = MutableLiveData<Boolean>()
    val posts = MutableLiveData<List<Posts>>()
    val postError = MutableLiveData<Boolean>()

    private val context = getApplication<Application>().applicationContext

    val sharedPreferences =
            context.getSharedPreferences("com.musaguzel.acicik", Context.MODE_PRIVATE)

    fun refreshData() {
        //getUserCity()
        getPostData()
    }

    fun getUserCity(){
        launch {
            auth.currentUser?.let {
                firebaseFirestore.collection("Users").document(it.uid) .addSnapshotListener { snapshot, error ->
                    if (error != null){
                        println(error.localizedMessage)
                    }else{
                        if (snapshot != null){
                             val city = snapshot["city"] as String
                             sharedPreferences.edit().putString("city",city).apply()
                            //alt kısım .whereArrayContains("city",)

                        }
                    }
                }
            }
        }
    }

    fun getPostData() {

        getUserCity()
        postLoading.value = true
        val city = sharedPreferences.getString("city","city")
        if (city.equals("city")){
            postError.value = true
        }else{
            launch {
                firebaseFirestore.collection("Menu_Cigkofte")
                        .whereArrayContains("city",city.toString())
                        .orderBy("date",Query.Direction.ASCENDING).addSnapshotListener { snapshot, exception ->
                            if (exception != null){
                                println(exception.localizedMessage)
                                postError.value = true
                            }else{
                                if (snapshot != null){
                                    if (!snapshot.isEmpty){
                                        val postList: List<Posts> = snapshot.toObjects(Posts::class.java)
                                        posts.value = postList
                                        postLoading.value = false
                                        postError.value = false
                                    }
                                }
                            }
                        }
            }
        }

    }



}