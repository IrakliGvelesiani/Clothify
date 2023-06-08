package com.example.clothify.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.clothify.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
    }
}