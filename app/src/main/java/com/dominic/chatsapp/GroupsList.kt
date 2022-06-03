package com.dominic.chatsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dominic.chatsapp.databinding.FragmentGroupsListBinding

class GroupsList : Fragment() {
    lateinit var binding:FragmentGroupsListBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       binding = FragmentGroupsListBinding.inflate(layoutInflater)



   //     Toast.makeText(requireContext(), "Groups", Toast.LENGTH_SHORT).show()
        return binding.root
    }


}