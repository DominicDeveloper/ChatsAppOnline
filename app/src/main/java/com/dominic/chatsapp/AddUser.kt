package com.dominic.chatsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dominic.chatsapp.Models.Users
import com.dominic.chatsapp.databinding.FragmentAddUserBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class AddUser : Fragment() {
    lateinit var binding:FragmentAddUserBinding
    lateinit var reference:DatabaseReference
    lateinit var database:FirebaseDatabase
    lateinit var usersList:ArrayList<Users>
    lateinit var auth:FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddUserBinding.inflate(layoutInflater)
        database = FirebaseDatabase.getInstance()
        reference = database.getReference("users")
        usersList = ArrayList()
        auth = FirebaseAuth.getInstance()
        database.reference
            .child("Users")
        binding.edtAdd.setOnClickListener {
            saveContact()
        }





        return binding.root
    }

    private fun saveContact() {


    }

}