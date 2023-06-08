package com.example.clothify.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothify.Util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignInViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): ViewModel() {

    private val _signIn = MutableSharedFlow<Resource<FirebaseUser>>()
    val signIn = _signIn.asSharedFlow()

    private val _resetPassword = MutableSharedFlow<Resource<String>>()
    val resetPassword = _resetPassword.asSharedFlow()

    fun signIn(email: String, password: String){
        viewModelScope.launch { _signIn.emit(Resource.Loading()) }
        firebaseAuth.signInWithEmailAndPassword(
            email, password
        ).addOnSuccessListener {
            viewModelScope.launch {
                it.user?.let {
                    _signIn.emit(Resource.Success(it))
                }
            }
        }.addOnFailureListener {
            viewModelScope.launch {
                _signIn.emit(Resource.Error(it.message.toString()))
            }
        }
    }

    fun resetPassword(email: String) {
        viewModelScope.launch {
            _resetPassword.emit(Resource.Loading())
        }

            firebaseAuth.sendPasswordResetEmail(email)
                .addOnSuccessListener {
                    viewModelScope.launch {
                        _resetPassword.emit(Resource.Success(email))
                    }
                }
                .addOnFailureListener {
                    viewModelScope.launch {
                        _resetPassword.emit(Resource.Error(it.message.toString()))
                    }
                }
        }

}