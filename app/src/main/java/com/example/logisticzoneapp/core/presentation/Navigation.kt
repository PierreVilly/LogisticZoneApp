package com.example.logisticzoneapp.core.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.logisticzoneapp.login_feature.presentation.LoginScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.LoginScreen.route
    ){
        composable(ScreenRoutes.ReplenishmentScreen.route){
        }

        composable(ScreenRoutes.DailyOperationScreen.route){
            Text(text = "Daily Operation Screen")
        }

        composable(ScreenRoutes.LoginScreen.route){
            LoginScreen(
                onLoginSuccessNavigation = {
                    navController.navigate(ScreenRoutes.ReplenishmentScreen.route){
                        popUpTo(0)
                    }
                },
            )
        }
    }
}

sealed class ScreenRoutes(val route:String){
    object ReplenishmentScreen: ScreenRoutes("replenishment_screen")
    object DailyOperationScreen: ScreenRoutes("daily_screen")
    object LoginScreen: ScreenRoutes("login_screen")
}