package com.example.money_management_app.data

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem (

    val title:String,
    val selectedIcon:ImageVector,
    val unselectedIcon:ImageVector,
    val hasNews:Boolean,
    val badgeCount: Int?=null
)