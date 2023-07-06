package com.itproger.note.ui.fragment.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.itproger.note.App.Companion.db
import com.itproger.note.data.Car
import com.itproger.note.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    val db = Firebase.firestore
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       binding.btnSave.setOnClickListener {
           setData()
       }
    }

    private fun setData() {
        val data = Car(
            label = binding.edtLabel.text.toString(),
            model = binding.edtModel.text.toString()

        )
        db.collection(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .document()
            .set(data)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Успешно сохранено!", Toast.LENGTH_SHORT).show()
            binding.edtLabel.text.clear()
                binding.edtModel.text.clear()
            }.addOnFailureListener{
                Log.e("ololo", "setData"+ it)
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}