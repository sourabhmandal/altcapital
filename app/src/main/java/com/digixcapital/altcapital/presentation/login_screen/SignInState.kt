package com.digixcapital.altcapital.presentation.login_screen

import com.google.firebase.auth.AuthResult

data class SignInState(
    val isLoading: Boolean = false,
    val isSuccess: AuthResult? = null,
    val isError: String? = ""
)