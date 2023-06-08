package com.example.clothify.Fragments.SignIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.clothify.R
import com.example.clothify.databinding.FragmentAccountOptionsBinding
import com.example.clothify.databinding.FragmentIntroductionBinding

class AccountOptionsFragment: Fragment(R.layout.fragment_account_options) {

    private lateinit var binding: FragmentAccountOptionsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountOptionsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signInButton.setOnClickListener{
            findNavController().navigate(R.id.action_accountOptionsFragment_to_signInFragment)
        }
        binding.signUpButton.setOnClickListener{
            findNavController().navigate(R.id.action_accountOptionsFragment_to_signUpFragment)
        }
    }
}
