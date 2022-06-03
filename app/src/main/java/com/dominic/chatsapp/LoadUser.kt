package com.dominic.chatsapp

import android.app.ProgressDialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dominic.chatsapp.Models.Users
import com.dominic.chatsapp.databinding.FragmentLoadUserBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class LoadUser : Fragment() {

    lateinit var loadUserBinding: FragmentLoadUserBinding

    var auth : FirebaseAuth? = null
    var database : FirebaseDatabase? = null

    var storage : FirebaseStorage? = null
    var dialog : ProgressDialog? = null

    var loadImageuri:String? = null
    var reference:StorageReference? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        loadUserBinding = FragmentLoadUserBinding.inflate(layoutInflater)

        dialog=ProgressDialog(requireContext())
        dialog!!.setMessage("Uploading Profile...")

        dialog!!.setCancelable(false)
        database = FirebaseDatabase.getInstance()

        storage = FirebaseStorage.getInstance()
        auth = FirebaseAuth.getInstance()
        reference = storage!!.getReference("Profile")

        loadUserBinding.imageView.setOnClickListener {
            getImageContent.launch("image/*")
        }
        loadUserBinding.continueBtn02.setOnClickListener {
               dialog!!.show()
               if (loadImageuri != null){
                   val uid = auth!!.uid
                   val name_u:String= loadUserBinding.nameBox.text.toString()
                   val phone = auth!!.currentUser!!.phoneNumber
                   val user = Users(uid,name_u,phone,loadImageuri)
                   database!!.reference
                       .child("users")
                       .child(uid!!)
                       .setValue(user)
                       .addOnCompleteListener {
                           dialog!!.dismiss()
                           findNavController().popBackStack()
                           findNavController().navigate(R.id.mainMenu)
                       }
               }else{
                   val uid = auth!!.uid
                   val name_u:String = loadUserBinding.nameBox.text.toString()
                   val phone = auth!!.currentUser!!.phoneNumber
                   val user = Users(uid,name_u,phone,"No image")
                   database!!.reference
                       .child("users")
                       .child(uid!!)
                       .setValue(user)
                       .addOnCompleteListener {
                           dialog!!.dismiss()
                           findNavController().popBackStack()
                           findNavController().navigate(R.id.mainMenu)
                       }


               }
           }


        return loadUserBinding.root
    }
    private var getImageContent = registerForActivityResult(ActivityResultContracts.GetContent()){ uri->
        loadUserBinding.imageView.setImageURI(uri)
       CustomDialog("Profile image is uploading...",false)
        val uploadTask = reference?.child(auth?.uid!!)?.putFile(uri)
       uploadTask?.addOnSuccessListener {
          if (it.task.isSuccessful){
              CustomDialog("Profile image has uploaded!",true)
              val downloadedUrl = it.metadata?.reference?.downloadUrl
              downloadedUrl?.addOnSuccessListener { imageUri ->
                  loadImageuri = imageUri.toString()
              }
          }
      }
    }
    private fun CustomDialog(message:String,hasDone:Boolean){
        val dialog = ProgressDialog(requireContext())
        dialog.setMessage(message)
        if (hasDone){
            dialog.dismiss()
        }else{
            dialog.show()
        }

    }
}
