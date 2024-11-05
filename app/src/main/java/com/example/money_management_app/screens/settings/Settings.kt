package com.example.money_management_app.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.money_management_app.ui.theme.VanillaCream

@Composable

fun Settings(navController: NavController) {

    Column (modifier = Modifier
        .fillMaxSize()
        .background(VanillaCream),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
Text(text = "Coming Soon",
        color = Color.Black,
        fontWeight = FontWeight.Bold,
         fontSize = 14.sp,)
     }
 }