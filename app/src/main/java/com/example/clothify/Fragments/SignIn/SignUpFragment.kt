package com.example.clothify.Fragments.SignIn

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.clothify.Data.User
import com.example.clothify.R
import com.example.clothify.Util.Resource
import com.example.clothify.Util.SignUpValidation
import com.example.clothify.Viewmodel.SignUpViewModel
import com.example.clothify.databinding.FragmentSignupBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext

private val TAG = "SignUpFragment"
@AndroidEntryPoint
class SignUpFragment: Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private val viewModel by viewModels<SignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentSignupBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.doyouhaveanaccount.setOnClickListener{
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        binding.apply {
            signInButton2.setOnClickListener {
                val user = User(
                    edFirstName.text.toString().trim(),
                    edLastName.text.toString().trim(),
                    edEmailSignUp.text.toString().trim()
                )
                val password = edPasswordSignUp.text.toString()
                viewModel.createAccountWithEmailAndPassword(user,password)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.signeup.collect{
                when(it){
                    is Resource.Loading -> {
                        binding.signInButton2.startAnimation()
                    }
                    is Resource.Success ->{
                        Log.d("test",it.message.toString())
                        binding.signInButton2.revertAnimation()

                    }
                    is Resource.Error -> {
                        Log.e(TAG,it.message.toString())
                        binding.signInButton2.revertAnimation()
                    }
                    else -> Unit
                }


            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.validation.collect{
                validation ->
                if(validation.email is SignUpValidation.Failed){
                    withContext(Dispatchers.Main){
                        binding.edEmailSignUp.apply {
                            requestFocus()
                            error = validation.email.message
                        }
                    }
                }
                if (validation.password is SignUpValidation.Failed){
                    withContext(Dispatchers.Main){
                        binding.edPasswordSignUp.apply {
                            requestFocus()
                            error = validation.password.message
                        }
                    }

                }
            }
        }
    }
}