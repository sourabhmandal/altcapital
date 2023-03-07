package com.digixcapital.altcapital.navigation

sealed class Screens(val route: String) {
    object LogInScreen : Screens(route = "login_screen")
    object RegisterScreen : Screens(route = "register_screen")
}
