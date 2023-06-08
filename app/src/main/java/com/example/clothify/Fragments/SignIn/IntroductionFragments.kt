package com.example.clothify.Fragments.SignIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.clothify.R
import com.example.clothify.databinding.FragmentIntroductionBinding
import com.example.clothify.databinding.FragmentSigninBinding

class IntroductionFragments: Fragment(R.layout.fragment_introduction) {

    private lateinit var binding: FragmentIntroductionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntroductionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.getStartedBtn.setOnClickListener{
            findNavController().navigate(R.id.action_introductionFragments_to_accountOptionsFragment)
        }
    }
}