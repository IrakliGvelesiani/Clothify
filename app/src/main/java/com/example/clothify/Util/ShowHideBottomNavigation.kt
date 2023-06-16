package com.example.clothify.Util

import android.view.View
import androidx.fragment.app.Fragment
import com.example.clothify.R
import com.example.clothify.Activities.ShoppingActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

fun Fragment.hideBottomNavigationView(){
    val bottomNavigationView =
        (activity as ShoppingActivity).findViewById<BottomNavigationView>(
            com.example.clothify.R.id.bottom_navigation
        )
    bottomNavigationView.visibility = android.view.View.GONE
}

fun Fragment.showBottomNavigationView(){
    val bottomNavigationView =
        (activity as ShoppingActivity).findViewById<BottomNavigationView>(
            com.example.clothify.R.id.bottom_navigation
        )
    bottomNavigationView.visibility = android.view.View.VISIBLE
}