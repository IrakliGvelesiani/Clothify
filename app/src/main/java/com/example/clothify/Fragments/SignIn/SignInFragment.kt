package com.example.clothify.Fragments.SignIn

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.clothify.Activities.ShoppingActivity
import com.example.clothify.R
import com.example.clothify.Util.Resource
import com.example.clothify.Viewmodel.SignInViewModel
import com.example.clothify.databinding.FragmentSigninBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SignInFragment: Fragment(R.layout.fragment_signin) {
    private lateinit var binding: FragmentSigninBinding
    private val viewModel by viewModels<SignInViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSigninBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.donthaveanaccount.setOnClickListener{
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        binding.apply {
            signInButton.setOnClickListener {
                val email = edEmailSignIn.text.toString().trim()
                val password = edPassword.text.toString()
                viewModel.signIn(email, password)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.signIn.collect{
                when(it){
                    is Resource.Loading -> {
                        binding.signInButton.startAnimation()

                    }
                    is Resource.Success -> {
                        binding.signInButton.revertAnimation()
                        Intent(requireActivity(), ShoppingActivity::class.java).also {intent ->
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }

                    }
                    is Resource.Error ->{
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                        binding.signInButton.revertAnimation()

                    }
                    else -> Unit
                }
            }
        }
    }
}