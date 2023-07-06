package com.itproger.note.ui.auth.phone

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.itproger.note.R
import com.itproger.note.databinding.FragmentPhoneBinding
import com.itproger.note.ui.activity.MainActivity
import java.util.concurrent.TimeUnit


@Suppress("DEPRECATION")
class PhoneFragment : Fragment() {
    private lateinit var binding: FragmentPhoneBinding
    private val auth by lazy { FirebaseAuth.getInstance() }
    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            auth.signInWithCredential(credential).addOnCompleteListener {
                if (it.isSuccessful) {
                    findNavController().navigate(R.id.navigation_home)
                } else {
                    Log.e("ololoAuth", it.exception?.message.toString())
                }
            }
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken,
        ) {
            findNavController().navigate(R.id.acceptFragment, bundleOf(VER_KEY to verificationId))
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
            Log.e("ololo", e.localizedMessage.toString())
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhoneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSend.setOnClickListener {
            val phone = binding.edtPhone.text.toString()
            val options = PhoneAuthOptions.newBuilder(auth).setPhoneNumber(phone)
                .setTimeout(60L, TimeUnit.SECONDS).setActivity(requireActivity())
                .setCallbacks(callbacks).build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        }
    }

    companion object {
        const val VER_KEY = "verification.key"
    }
}

