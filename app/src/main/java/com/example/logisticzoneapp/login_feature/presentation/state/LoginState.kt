package com.example.logisticzoneapp.login_feature.presentation.state

data class LoginState(
    val usernameInput: String = "",
    val passwordInput: String = "",
    val isInputValid: Boolean = false,
    val isPasswordShown: Boolean = false,
    val errorMessageInput: String? = null,
    val isLoading: Boolean = false,
    val isSuccessfullyLoggedIn: Boolean = false,
    val errorMessageLoginProcess: String? = null
)
