package com.example.logisticzoneapp.login_feature.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logisticzoneapp.core.data.api.MovexApiService
import com.example.logisticzoneapp.core.util.BasicAuthInterceptor
import com.example.logisticzoneapp.login_feature.domain.model.LoginInputValidationType
import com.example.logisticzoneapp.login_feature.domain.model.MovexUser
import com.example.logisticzoneapp.login_feature.domain.use_case.ValidateLoginInputUseCase
import com.example.logisticzoneapp.login_feature.presentation.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateLoginInputUseCase: ValidateLoginInputUseCase,
    private val movexApiService: MovexApiService
) : ViewModel() {

    var loginState by mutableStateOf(LoginState())
        private set

    fun onUsernameInputChange(newValue: String){
        loginState = loginState.copy(usernameInput = newValue)
        checkInputValidation()
    }

    fun onPasswordInputChange(newValue: String){
        loginState = loginState.copy(passwordInput = newValue)
        checkInputValidation()
    }

    fun onToggleVisualTransformation(){
        loginState = loginState.copy(isPasswordShown =  !loginState.isPasswordShown)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onLoginClick(){
        loginState = loginState.copy(isLoading = true)
        viewModelScope.launch {
            loginState = try {
                val credentials = MovexUser(
                    username = loginState.usernameInput,
                    password = Base64.getEncoder().encodeToString(loginState.passwordInput.toByteArray())
                )

                val response = movexApiService.login(credentials = credentials)
                if(response.isSuccessful){
                    BasicAuthInterceptor.setCredentials(credentials.username, credentials.password)
                    loginState.copy(isSuccessfullyLoggedIn = true)
                } else {
                    loginState.copy(isSuccessfullyLoggedIn = false, errorMessageInput = response.errorBody()!!.string())
                }
            } catch (e: Exception){
                loginState.copy(errorMessageLoginProcess = "Could not login")
            } finally {
                loginState = loginState.copy(isLoading = false)
            }
        }
    }

    private fun checkInputValidation(){
        val validationResult = validateLoginInputUseCase(
            loginState.usernameInput,
            loginState.passwordInput
        )
        processInputValidationType(validationResult)
    }

    private fun processInputValidationType(type: LoginInputValidationType){
        loginState = when(type){
            LoginInputValidationType.EmptyField -> {
                loginState.copy(errorMessageInput = "Empty fields left", isInputValid = false)
            }
            LoginInputValidationType.Valid -> {
                loginState.copy(errorMessageInput = null, isInputValid = true)
            }
        }
    }
}