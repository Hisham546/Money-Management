package com.example.money_management_app.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.money_management_app.R
import com.example.money_management_app.data.BottomNavigationItem
import com.example.money_management_app.ui.theme.VanillaBottom
import com.example.money_management_app.ui.theme.VanillaCream

import kotlin.math.round


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(navController: NavHostController) {

    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = "Add",
//            selectedIcon = R.drawable.analysis,
//            unselectedIcon = R.drawable.analysis,

            selectedIcon = Icons.Filled.Add,
            unselectedIcon = Icons.Outlined.AddCircle,

            hasNews = false,
        ),
        BottomNavigationItem(
            title = "Settings",

            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            hasNews = false,
        )
    )

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0) // Set the default selected item index to 0 (Home)
    }


    Box(
      modifier = Modifier
          .fillMaxWidth()
          .clip(
              RoundedCornerShape(
                  topStart = 36.dp,
                  topEnd = 36.dp,
                  bottomEnd = 0.dp,
                  bottomStart = 0.dp,
              ))
          .background(VanillaBottom)
    ) {


    NavigationBar(
        modifier = Modifier
            .height(67.dp),
        containerColor = Color.Transparent,


        ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    when (index) {

                        0 -> navController.navigate("home_screen")
                        1 -> navController.navigate("add_amount_screen")
                        1 -> navController.navigate("settings")
                    }
                },
//                label = {
//                    Text(
//                        text = item.title,
//                        fontSize = 10.sp
//                    )
//                },
                icon = {
                    BadgedBox(badge = {
                        if (item.badgeCount != null) {
                            Badge {
                                Text(text = item.badgeCount.toString())
                            }
                        } else if (item.hasNews) {
                            Badge()
                        }
                    }) {

                            Icon(
                                imageVector = if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.title,
                                modifier = Modifier
                                    .size(25.dp)


                            )

                    }

                }
            )
        }
    }
}
}


