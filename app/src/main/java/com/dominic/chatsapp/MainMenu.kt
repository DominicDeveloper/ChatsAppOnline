package com.dominic.chatsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.dominic.chatsapp.Adapters.MyTabAdapter
import com.dominic.chatsapp.databinding.FragmentMainMenuBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainMenu : Fragment() {

    lateinit var binding:FragmentMainMenuBinding

    lateinit var listtab :ArrayList<String>

    lateinit var myTabAdapter: MyTabAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainMenuBinding.inflate(layoutInflater)

        val tab:TabLayout = binding.root.findViewById(R.id.my_tab)

        val viewPager2:ViewPager2 = binding.root.findViewById(R.id.my_viewpager)

        listtab = ArrayList()

        listtab.add("Users")

        listtab.add("Groups")

        myTabAdapter = MyTabAdapter(requireActivity(),listtab)

        viewPager2.adapter = myTabAdapter

        TabLayoutMediator(tab,viewPager2,(TabLayoutMediator.TabConfigurationStrategy { tab, position ->  tab.setText(listtab[position])})).attach()


        return binding.root
    }


}