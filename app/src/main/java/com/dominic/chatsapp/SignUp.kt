package com.dominic.chatsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.dominic.chatsapp.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_sign_up.*


class SignUp : Fragment() {
    lateinit var binding: FragmentSignUpBinding
    var auth:FirebaseAuth? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       binding = FragmentSignUpBinding.inflate(layoutInflater)

        auth = FirebaseAuth.getInstance()
        if (auth!!.currentUser != null){
            findNavController().popBackStack()
            findNavController().navigate(R.id.mainMenu)
        }
        binding.edtNumber.requestFocus()
        binding.continueBtn.setOnClickListener {
            findNavController().popBackStack()
            findNavController().navigate(R.id.verfry, bundleOf("phoneNumber" to edt_Number.text.toString()))
        }

        return binding.root
    }

}