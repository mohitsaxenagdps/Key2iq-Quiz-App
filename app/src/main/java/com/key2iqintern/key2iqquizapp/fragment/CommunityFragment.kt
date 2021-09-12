package com.key2iqintern.key2iqquizapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.key2iqintern.key2iqquizapp.R
import com.key2iqintern.key2iqquizapp.activity.MainActivity
import com.key2iqintern.key2iqquizapp.databinding.FragmentCommunityBinding

class CommunityFragment : Fragment() {

    private lateinit var binding: FragmentCommunityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommunityBinding.inflate(inflater, container, false)

        binding.mainLayout.btCompeteNow.setOnClickListener {
            val action = R.id.action_communityFragment_to_quizFragment
            findNavController().navigate(action)
            MainActivity.binding.appBar.visibility = View.GONE
            MainActivity.binding.bottomNavigation.visibility = View.GONE
        }

        return binding.root
    }

}