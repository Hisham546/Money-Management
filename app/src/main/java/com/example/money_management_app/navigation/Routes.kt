package com.example.money_management_app.navigation

sealed class Screens(val route: String) {
    object Home: Screens("home_screen")
    object AddAmount:Screens("add_amount_screen")
    object  Analysis:Screens("analysis")



}