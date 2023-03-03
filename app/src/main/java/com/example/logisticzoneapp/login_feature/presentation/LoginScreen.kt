package com.example.logisticzoneapp.login_feature.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.logisticzoneapp.core.presentation.SystemBroadcastReceiver
import com.example.logisticzoneapp.login_feature.presentation.components.AuthButton
import com.example.logisticzoneapp.login_feature.presentation.components.NavDestinationHelper
import com.example.logisticzoneapp.login_feature.presentation.components.TextEntryModule
import com.example.logisticzoneapp.ui.theme.gray
import com.example.logisticzoneapp.ui.theme.orange
import com.example.logisticzoneapp.ui.theme.white
import com.example.logisticzoneapp.ui.theme.whiteGray

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun LoginScreen(
    onLoginSuccessNavigation: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    NavDestinationHelper(
        shouldNavigate = {
            loginViewModel.loginState.isSuccessfullyLoggedIn
        },
        destination = {
            onLoginSuccessNavigation()
        }
    )

    if(Build.MANUFACTURER == "Zebra Technologies"){
        SystemBroadcastReceiver(systemAction = "com.example.zebradatawedgebroadcastintent.SCAN"){
            receiverState ->
            val action = receiverState?.action ?: return@SystemBroadcastReceiver
            if(action == "com.example.zebradatawedgebroadcastintent.SCAN") {
//                TODO : Handle Barcode
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.h4,
                color = white,
                fontWeight = FontWeight.SemiBold
            )
        }
        LoginContainer(
            usernameValue = {
                loginViewModel.loginState.usernameInput
            },
            passwordValue = {
                loginViewModel.loginState.passwordInput
            },
            buttonEnabled = {
                loginViewModel.loginState.isInputValid
            },
            onUsernameChanged = loginViewModel::onUsernameInputChange,
            onPasswordChanged = loginViewModel::onPasswordInputChange,
            onLoginButtonClick = loginViewModel::onLoginClick,
            isPasswordShown = {
                loginViewModel.loginState.isPasswordShown
            },
            onTrailingPasswordIconClick = { loginViewModel.onToggleVisualTransformation() },
            errorHint = {
                loginViewModel.loginState.errorMessageInput
            },
            isLoading = {
                loginViewModel.loginState.isLoading
            },
            modifier = Modifier
                .padding(top = 200.dp)
                .fillMaxWidth(0.9F)
                .shadow(5.dp, RoundedCornerShape(15.dp))
                .background(whiteGray, RoundedCornerShape(15.dp))
                .padding(10.dp, 15.dp, 10.dp, 15.dp)
                .align(Alignment.TopCenter)
        )
    }
}

@Composable
fun LoginContainer(
    usernameValue: () -> String,
    passwordValue: () -> String,
    buttonEnabled: () -> Boolean,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginButtonClick: () -> Unit,
    isPasswordShown: () -> Boolean,
    onTrailingPasswordIconClick: () -> Unit,
    errorHint: () -> String?,
    isLoading: () -> Boolean,
    modifier: Modifier = Modifier
){
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(15.dp)) {
        TextEntryModule(
            modifier = Modifier
                .fillMaxWidth(),
            description = "Email address",
            hint = "KApps@gmail.com",
            textValue = usernameValue(),
            textColor = gray,
            cursorColor = orange,
            onValueChanged = onUsernameChanged,
            trailingIcon = null,
            onTrailingIconClick = null,
            leadingIcon = Icons.Default.VerifiedUser
        )
        TextEntryModule(
            modifier = Modifier
                .fillMaxWidth(),
            description = "Password",
            hint = "Enter password",
            textValue = passwordValue(),
            textColor = gray,
            cursorColor = orange,
            onValueChanged = onPasswordChanged,
            trailingIcon = Icons.Default.RemoveRedEye,
            onTrailingIconClick = {
                onTrailingPasswordIconClick()
            },
            leadingIcon = Icons.Default.VpnKey,
            visualTransformation = if(isPasswordShown()){
                VisualTransformation.None
            }else PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            AuthButton(
                text = "Login",
                backgroundColor = orange,
                contentColor = white,
                enabled = buttonEnabled(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .shadow(5.dp, RoundedCornerShape(25.dp)),
                onButtonClick = onLoginButtonClick,
                isLoading = isLoading(),
            )
            Text(
                text = errorHint() ?: "",
                style = MaterialTheme.typography.caption
            )
        }
    }
}