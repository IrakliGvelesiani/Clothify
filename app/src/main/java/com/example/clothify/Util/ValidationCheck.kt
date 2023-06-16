package com.example.clothify.Util

import android.util.Patterns

fun validateEmail(email: String): SignUpValidation{
    if (email.isEmpty())
        return SignUpValidation.Failed("Email Can't Be Empty")

    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        return SignUpValidation.Failed("Wrong Email Format")

    return SignUpValidation.Success
}

fun validatePassword(password: String): SignUpValidation{
    if (password.isEmpty())
        return SignUpValidation.Failed("Password Can't Be Empty")

    if (password.length < 6)
        return SignUpValidation.Failed("Password should contain 6 char")

    return SignUpValidation.Success
}