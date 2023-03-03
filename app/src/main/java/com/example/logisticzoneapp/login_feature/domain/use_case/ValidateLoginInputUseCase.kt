package com.example.logisticzoneapp.login_feature.domain.use_case

import com.example.logisticzoneapp.login_feature.domain.model.LoginInputValidationType

class ValidateLoginInputUseCase() {
    operator fun invoke(username: String, password: String) : LoginInputValidationType {
        if(username.isEmpty() || password.isEmpty()){
            return LoginInputValidationType.EmptyField
        }
        return LoginInputValidationType.Valid
    }
}