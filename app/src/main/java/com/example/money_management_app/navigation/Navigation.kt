package com.example.money_management_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.money_management_app.screens.addamount.AddAmount
import com.example.money_management_app.screens.HomeScreen
import com.example.money_management_app.screens.analysis.Analysis

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    )
    {
        composable(route = Screens.Home.route) {
            HomeScreen(navController)
        }
        composable(route = Screens.AddAmount.route) {
            AddAmount(navController)
        }
        composable(route=Screens.Analysis.route){
            Analysis(navController)
        }
    }

}