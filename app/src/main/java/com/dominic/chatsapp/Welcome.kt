package com.dominic.chatsapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.fragment.findNavController
import com.dominic.chatsapp.databinding.FragmentWelcomeBinding

class Welcome : Fragment() {
    lateinit var binding:FragmentWelcomeBinding
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       binding = FragmentWelcomeBinding.inflate(layoutInflater)

        anim(binding.myStaticChatIcon)

        return binding.root
    }

    private fun anim(view:View) {
        val anim = AnimationUtils.loadAnimation(requireContext(),R.anim.welcome_anim)
        view.startAnimation(anim)
        anim.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
                startSecondAnim(binding.myStaticChatText1,binding.myStaticChatTex2)
            }

            override fun onAnimationEnd(p0: Animation?) {


            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })
    }
    private fun startSecondAnim(view1: View,view2:View) {
        val anim = AnimationUtils.loadAnimation(requireContext(),R.anim.text_anim_from_left)
        view1.startAnimation(anim)
        val animRight = AnimationUtils.loadAnimation(requireContext(),R.anim.text_anim_from_right)
        view2.startAnimation(animRight)
        animRight.setAnimationListener(object :Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                val handler = Handler()
                handler.postDelayed({
                    if (hasNetwork() == true){
                        findNavController().popBackStack()
                        findNavController().navigate(R.id.signUp)
                        Toast.makeText(requireContext(), "Is network working good", Toast.LENGTH_SHORT).show()
                    }else{
                        anim(binding.myStaticChatIcon)
                        Toast.makeText(requireContext(), "Connect to network!", Toast.LENGTH_SHORT).show()
                    }
                },1500)
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })
    }
    private fun hasNetwork():Boolean{
        val connectivityManager = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            val activeNetwork = connectivityManager.activeNetwork
            val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)

            return networkCapabilities != null && networkCapabilities.hasCapability(
                NetworkCapabilities.NET_CAPABILITY_INTERNET)

        }else{

            val activeNetWorkInfo = connectivityManager.activeNetworkInfo

            return activeNetWorkInfo != null && activeNetWorkInfo.isConnected
        }
    }


}
