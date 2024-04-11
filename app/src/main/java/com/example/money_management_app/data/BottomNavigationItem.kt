package com.example.money_management_app.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import kotlin.coroutines.CoroutineContext


data class BottomNavigationItem (

    val title:String,
    val selectedIcon:ImageVector,
    val unselectedIcon:ImageVector,
    val hasNews:Boolean,
    val badgeCount: Int?=null
)