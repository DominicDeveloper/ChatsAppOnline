package com.dominic.chatsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dominic.chatsapp.databinding.FragmentGroupChatBinding

class FragmentGroupChat : Fragment() {
    lateinit var binding:FragmentGroupChatBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       binding = FragmentGroupChatBinding.inflate(layoutInflater)



        return binding.root
    }


}