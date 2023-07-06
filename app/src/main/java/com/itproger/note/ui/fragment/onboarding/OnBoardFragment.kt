package com.itproger.note.ui.fragment.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.itproger.note.R
import com.itproger.note.data.local.Pref
import com.itproger.note.databinding.FragmentOnBoardBinding
import com.itproger.note.ui.fragment.onboarding.adapter.OnBoardAdapter

class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding
    private val adapter = OnBoardAdapter(this::onClick)
    private val pref: Pref by lazy {
        Pref(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pager.adapter = adapter
        TabLayoutMediator(binding.wormDotsIndicator, binding.pager) { tab, _ ->
            tab.setIcon(R.drawable.tab_indicator)
        }.attach()
    }

    private fun onClick() {
        pref.saveSeen()
        pref.saveName(String())
        findNavController().navigate(R.id.action_to_mobile_navigation)
    }


}