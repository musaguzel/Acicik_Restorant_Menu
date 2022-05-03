package com.musaguzel.acicik.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.musaguzel.acicik.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_credential.*
import java.util.concurrent.TimeUnit


class CredentialFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    lateinit var storedVerificationId:String
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_credential, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth
        getOtp()

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {


            }

            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(requireContext(),"auth failed",Toast.LENGTH_LONG).show()
                progressBar.visibility = View.GONE
                btnGiris.visibility = View.VISIBLE

            }

            override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
            ) {

                storedVerificationId = verificationId
                resendToken = token
                //Diger fragmenta gidilecek
                progressBar.visibility = View.GONE
                btnGiris.visibility = View.VISIBLE
                val action = CredentialFragmentDirections.actionCredentialFragmentToOtpFragment(
                    editTextTextPhone.text.toString()
                    ,storedVerificationId,textViewCity.text.toString(),editTextName.text.toString(),editTextSurname.text.toString())
                Navigation.findNavController(view).navigate(action)

            }
        }


    }

    private fun getOtp() {
        btnGiris.setOnClickListener {

            val city = textViewCity.text.toString().trim()
            val name = editTextName.text.toString().trim()
            val surname = editTextSurname.text.toString().trim()
            val phoneNumber = editTextTextPhone.text.toString().trim()

            println(city + " " + name + " " + surname + " " + phoneNumber)

            if (city.equals("") || name.equals("") || surname.equals("") || phoneNumber.equals("")) {
                Toast.makeText(context, "Lütfen Bilgileri Eksiksiz Girin", Toast.LENGTH_LONG).show()
            } else {
                progressBar.visibility = View.VISIBLE
                btnGiris.visibility = View.INVISIBLE

                // send a verification code from firebase

                val options = PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber("+90$phoneNumber")       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this.requireActivity())                 // Activity (for callback binding)
                        .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
                        .build()
                PhoneAuthProvider.verifyPhoneNumber(options)

            }
        }

    }
}