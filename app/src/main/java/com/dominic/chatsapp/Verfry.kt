package com.dominic.chatsapp

import android.app.ProgressDialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dominic.chatsapp.databinding.FragmentVerfryBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_verfry.*
import java.util.concurrent.TimeUnit

class Verfry : Fragment() {
    lateinit var binding: FragmentVerfryBinding

    var verificationId:String? = null

    var auth:FirebaseAuth? = null

    var dialog: ProgressDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentVerfryBinding.inflate(layoutInflater)

        dialog = ProgressDialog(requireContext())

        dialog!!.setMessage("Sending DATA...")

        dialog!!.setCancelable(false)

        dialog!!.show()

        auth = FirebaseAuth.getInstance()

        val phoneNumber = arguments?.getString("phoneNumber")

        binding.phoneLble.text = "Verify $phoneNumber"
        val options = PhoneAuthOptions.newBuilder(auth!!)
            .setPhoneNumber(phoneNumber!!)
            .setTimeout(60L,TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                override fun onVerificationCompleted(p0: PhoneAuthCredential) {

                }

                override fun onVerificationFailed(p0: FirebaseException) {


                }

                override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(p0,p1)
                    dialog!!.dismiss()
                    verificationId = p0
                    val imm2 = activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm2.toggleSoftInput(InputMethodManager.SHOW_FORCED,0)
                    binding.otpView.requestFocus()

                }
            }).build()

        PhoneAuthProvider.verifyPhoneNumber(options)

        binding.otpView.setOtpCompletionListener {

            val credential=PhoneAuthProvider.getCredential(verificationId!!, otp_view.text.toString())

            auth!!.signInWithCredential(credential)
                .addOnCompleteListener { taskId ->
                    if (taskId.isSuccessful) {
                        findNavController().popBackStack()
                        findNavController().navigate(R.id.loadUser)
                    } else {
                        Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }
        return binding.root
    }

}
