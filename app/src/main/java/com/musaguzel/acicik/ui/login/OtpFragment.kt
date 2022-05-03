package com.musaguzel.acicik.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.musaguzel.acicik.MainActivity
import com.musaguzel.acicik.R
import kotlinx.android.synthetic.main.fragment_otp.*

class OtpFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    private lateinit var verificationId: String
    private lateinit var phoneNumber: String
    private lateinit var city: String
    private lateinit var name: String
    private lateinit var surname: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_otp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth
        firestore = Firebase.firestore

        setupOTPinputs()

        arguments?.let {
            phoneNumber = OtpFragmentArgs.fromBundle(it).phoneNumber
            verificationId = OtpFragmentArgs.fromBundle(it).verificationCode
            city = OtpFragmentArgs.fromBundle(it).city
            name = OtpFragmentArgs.fromBundle(it).name
            surname = OtpFragmentArgs.fromBundle(it).surname
        }
        buttonOtp()
    }

    private fun buttonOtp(){
        button_otp.setOnClickListener {
            if (inputCode1.text.toString().trim().isEmpty()
                || inputCode2.text.toString().trim().isEmpty()
                ||inputCode3.text.toString().trim().isEmpty()
                ||inputCode4.text.toString().trim().isEmpty()
                ||inputCode5.text.toString().trim().isEmpty()
                ||inputCode6.text.toString().trim().isEmpty()){
                Toast.makeText(context,"Lütfen Doğrulama kodunu girin",Toast.LENGTH_LONG).show()
            }

            val code = inputCode1.text.toString() +
                    inputCode2.text.toString() +
                    inputCode3.text.toString() +
                    inputCode4.text.toString() +
                    inputCode5.text.toString() +
                    inputCode6.text.toString()

            if (verificationId != null){
                progressBar_Otp.visibility = View.VISIBLE
                button_otp.visibility = View.INVISIBLE

                val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
                auth.signInWithCredential(credential)
                    .addOnCompleteListener() { task ->
                        progressBar_Otp.visibility = View.GONE
                        button_otp.visibility = View.VISIBLE
                        if (task.isSuccessful) {

                            val intent = Intent(this.requireActivity(),MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)


                            //veritabanına bilgiler kayıt edilecek.

                            val infoMap = HashMap<String,Any>()
                            infoMap.put("city",city)
                            infoMap.put("name",name)
                            infoMap.put("phone",phoneNumber)
                            infoMap.put("surname",surname)
                            saveUserInfo(infoMap)

                            val user = task.result?.user
                        } else {
                            Toast.makeText(context,"Girilen kod yanlış veya hatalı",Toast.LENGTH_LONG).show()
                            if (task.exception is FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(context,"Girilen kod yanlış veya hatalı",Toast.LENGTH_LONG).show()
                            }
                            // Update UI
                        }
                    }
            }
        }
    }

    fun saveUserInfo(infoMap:HashMap<String,Any>){

        auth.currentUser?.let {
            firestore.collection("Users").document(it.uid).set(infoMap).addOnSuccessListener {

            }.addOnFailureListener {
                Toast.makeText(context,it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupOTPinputs() {
        inputCode1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!p0.toString().trim().isEmpty()) {
                    inputCode2.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        inputCode2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!p0.toString().trim().isEmpty()) {
                    inputCode3.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        inputCode3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!p0.toString().trim().isEmpty()) {
                    inputCode4.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        inputCode4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!p0.toString().trim().isEmpty()) {
                    inputCode5.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        inputCode5.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!p0.toString().trim().isEmpty()) {
                    inputCode6.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }
}