package com.dominic.chatsapp
import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dominic.chatsapp.Adapters.UserAdapter
import com.dominic.chatsapp.Models.Users
import com.dominic.chatsapp.databinding.FragmentMainListBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainListFragment : Fragment() {
    lateinit var binding:FragmentMainListBinding

    var database:FirebaseDatabase? = null

    var userList : ArrayList<Users>? = null

    var userAdapter:UserAdapter? = null

    var dialog:ProgressDialog? = null

    var users:Users? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainListBinding.inflate(layoutInflater)
        dialog = ProgressDialog(requireContext())
        dialog!!.setMessage("Uploading Image...")
        dialog!!.setCancelable(false)
        database = FirebaseDatabase.getInstance()
        userList = ArrayList()
        userAdapter = UserAdapter(requireContext(),userList!!,object :UserAdapter.RvClick{
            override fun onClick(users: Users) {
                findNavController().navigate(R.id.chatFragment, bundleOf("user" to users))
            }

        })

       database!!.reference.child("users")
           .child(FirebaseAuth.getInstance().uid!!)
           .addValueEventListener(object : ValueEventListener{
               override fun onDataChange(snapshot: DataSnapshot) {
                   users = snapshot.getValue(Users::class.java)
               }
               override fun onCancelled(error: DatabaseError) {

               }
           })

        database!!.reference.child("users").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                userList!!.clear()
                for (snapshot1 in snapshot.children){
                    val users : Users? = snapshot1.getValue(Users::class.java)
                   if (!users!!.uid.equals(FirebaseAuth.getInstance().uid)) userList!!.add(users) // bu kod ishlatilgan basedan bitta user malumoti kelyabdi
                }
                userAdapter!!.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {

            }

        })
        binding.addUser.setOnClickListener {

            addUser()

        }
        binding.myRv.adapter = userAdapter

        return binding.root
    }

    override fun onResume(){
        super.onResume()
        val  currentId = FirebaseAuth.getInstance().uid
        database!!.reference.child("presence")
            .child(currentId!!).setValue("Online")
    }

    override fun onPause(){
        super.onPause()
        val  currentId = FirebaseAuth.getInstance().uid
        database!!.reference.child("presence")
            .child(currentId!!).setValue("Offline")
    }

    private fun addUser(){
        findNavController().navigate(R.id.addUser)
    }
}