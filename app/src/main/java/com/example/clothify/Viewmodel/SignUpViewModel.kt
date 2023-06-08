package com.example.clothify.Viewmodel

import androidx.lifecycle.ViewModel
import com.example.clothify.Data.User
import com.example.clothify.Util.Resource
import com.example.clothify.Util.SignUpFieldState
import com.example.clothify.Util.SignUpValidation
import com.example.clothify.Util.validateEmail
import com.example.clothify.Util.validatePassword
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking

import javax.inject.Inject

@HiltViewModel

class SignUpViewModel @Inject constructor(
   private val firebaseAuth: FirebaseAuth
): ViewModel() {

    private val _sigenup = MutableStateFlow<Resource<FirebaseUser>>(Resource.Unspecified())
    val signeup: Flow<Resource<FirebaseUser>> = _sigenup

    private val _validation = Channel<SignUpFieldState> (  )
    val validation = _validation.receiveAsFlow()


    fun createAccountWithEmailAndPassword(user: User, password: String) {
        if (checkValidation(user, password)){

        runBlocking {
            _sigenup.emit(Resource.Loading())
        }
        firebaseAuth.createUserWithEmailAndPassword(user.email, password)
            .addOnSuccessListener {
                it.user?.let{
                    _sigenup.value = Resource.Success(it)
                }
            }.addOnFailureListener {
                    _sigenup.value = Resource.Error(it.message.toString())
            }
            }else{
                val signupFieldState = SignUpFieldState(
                    validateEmail(user.email),validatePassword(password)
                )

            runBlocking {
                _validation.send(signupFieldState)
            }
        }
    }

    private fun checkValidation(user: User, password: String): Boolean {
        val emailValidation = validateEmail(user.email)
        val passwordValidation = validatePassword(password)
        val shouldRegister = emailValidation is SignUpValidation.Success &&
                passwordValidation is SignUpValidation.Success

        return shouldRegister
    }
}

