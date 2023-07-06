package com.itproger.note.ui.fragment.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.itproger.note.data.local.Pref
import com.itproger.note.databinding.FragmentProfileBinding

@Suppress("DEPRECATION", "UNREACHABLE_CODE")
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val pref: Pref by lazy {
        Pref(requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profileEdtName.setText(pref.getName())
        binding.profileEdtLastname.setText(pref.getLastname())
        binding.profileSaveBtn.setOnClickListener {
            pref.saveName(binding.profileEdtName.text.toString())
            pref.saveLastname(binding.profileEdtLastname.text.toString())
        }
        binding.profileSelectImage.setOnClickListener {
            openGallaryForImage()
        }
    }

    private fun openGallaryForImage() {
        resultLauncher.launch("image/*")
    }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) {
        binding.profileSelectImage.setImageURI(it)
    }
}

