package com.digixcapital.altcapital.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.digixcapital.altcapital.presentation.login_screen.LoginUI
import com.digixcapital.altcapital.presentation.register_screen.RegisterUI


@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.LogInScreen.route
    ) {
        composable(route = Screens.LogInScreen.route) {
            LoginUI(navController = navController)
        }
        composable(route = Screens.RegisterScreen.route) {
            RegisterUI(navController = navController)
        }
    }

}