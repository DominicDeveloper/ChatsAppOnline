package com.dominic.chatsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.dominic.chatsapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


      my_static_fragment.findNavController().addOnDestinationChangedListener(object : NavController.OnDestinationChangedListener{
          override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
              when(destination.id){
                  R.id.chatFragment -> binding.root.mymaininclude.isVisible = false
                  R.id.fragmentGroupChat -> binding.root.mymaininclude.isVisible = false
                  R.id.welcome -> binding.root.mymaininclude.isVisible = false
                  else -> binding.root.mymaininclude.isVisible = true
              }
          }
      })

    }
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.my_navigation).navigateUp()

    }
}