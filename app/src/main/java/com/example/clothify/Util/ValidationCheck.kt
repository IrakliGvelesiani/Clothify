package com.example.clothify.Util

import android.util.Patterns

fun validateEmail ( email: String): SignUpValidation{
    if (email.isEmpty())
        return SignUpValidation.Failed("Email Can't be Empty!")
    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        return SignUpValidation.Failed("Wrong Email Format!")

    return SignUpValidation.Success
}

fun validatePassword(password: String): SignUpValidation{
    if(password.isEmpty())
        return SignUpValidation.Failed("Password can't be Empty!")

    if(password.length < 6)
        return SignUpValidation.Failed("Password Should Contain 6 Character!")

    return SignUpValidation.Success
}