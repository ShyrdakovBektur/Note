package com.itproger.note.ui.auth.accept

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.itproger.note.R
import com.itproger.note.databinding.FragmentAcceptBinding
import com.itproger.note.ui.auth.phone.PhoneFragment


class AcceptFragment : Fragment() {

    private lateinit var binding: FragmentAcceptBinding
    private val auth by lazy { FirebaseAuth.getInstance() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAcceptBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAccept.setOnClickListener {
            val verId: String? = arguments?.getString(PhoneFragment.VER_KEY)
            if (verId != null) {
                val credential =
                    PhoneAuthProvider.getCredential(verId, binding.edtCode.enteredCode)
                signInWithPhoneAuthCredential(credential)
            } else {
                Toast.makeText(requireContext(), "Verification ID is null", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnSuccessListener {
                findNavController().navigate(R.id.action_to_onBoard)
            }
            .addOnFailureListener { exception ->
                Toast.makeText(requireContext(), exception.message, Toast.LENGTH_SHORT).show()
            }
            .addOnCanceledListener {
                Toast.makeText(
                    requireContext(),
                    "Phone authentication canceled",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}
