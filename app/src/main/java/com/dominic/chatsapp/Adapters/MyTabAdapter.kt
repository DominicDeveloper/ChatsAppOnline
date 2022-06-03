package com.dominic.chatsapp.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dominic.chatsapp.GroupsList
import com.dominic.chatsapp.MainListFragment

class MyTabAdapter(fragmentActivity: FragmentActivity, val title:ArrayList<String>): FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount(): Int {
            return title.size
        }

        override fun createFragment(position: Int): Fragment {

            when (position) {
                0 -> return MainListFragment()
                1 -> return GroupsList()


            }
            return MainListFragment()
        }
    }
