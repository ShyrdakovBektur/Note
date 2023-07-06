package com.itproger.note.ui.fragment.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.itproger.note.data.Car
import com.itproger.note.databinding.FragmentNotificationsBinding
import com.itproger.note.ui.fragment.notifications.adapter.CarAdapter

class NotificationsFragment : Fragment() {

    val db = Firebase.firestore
    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    private val adapter = CarAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        db.collection(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .get().addOnSuccessListener {
                val list = it.toObjects(Car::class.java)
                adapter.setCars(list)
            }.addOnFailureListener {
                Log.e("ololo", "onViewCreated: $it")
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}