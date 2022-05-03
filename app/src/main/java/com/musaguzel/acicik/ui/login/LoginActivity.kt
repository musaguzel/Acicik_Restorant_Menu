package com.musaguzel.acicik.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.musaguzel.acicik.MainActivity
import com.musaguzel.acicik.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_credential.*
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth
            /////////////////////////
        val currentUser = auth.currentUser

        if (currentUser != null){
            val intent = Intent(this,MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        ///////////////////////////////
    }


    override fun onResume() {
        super.onResume()

        val cities = resources.getStringArray(R.array.Cities)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, cities)
        textViewCity.setAdapter(arrayAdapter)
    }
}