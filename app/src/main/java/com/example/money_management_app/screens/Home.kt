package com.example.money_management_app.screens

import android.graphics.drawable.Icon
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row


import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.room.Transaction
import com.example.money_management_app.R
import com.example.money_management_app.navigation.BottomNavigationBar
import com.example.money_management_app.room.Expense
import com.example.money_management_app.room.MyApp
import com.example.money_management_app.room.Income
import com.example.money_management_app.screens.addamount.FinanceViewModel
import com.example.money_management_app.ui.theme.BlackShade
import com.example.money_management_app.ui.theme.SkyBlue
import com.example.money_management_app.ui.theme.VanillaCream
import com.example.money_management_app.ui.theme.VanillaLight
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel: FinanceViewModel = viewModel()


    val recentTransactionList by viewModel.transactionHistory.observeAsState()
    @Composable
    fun VerticalLine() {
        Box(
            modifier = Modifier
                .width(1.dp) // Width of the line
                .height(30.dp) // Height of the line
                .background(VanillaCream) // Color of the line
        )
    }



    LaunchedEffect(key1 = true) {


        viewModel.fetchRecentTransaction()


    }

    Column(modifier = Modifier
        .background(VanillaCream)) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .background(VanillaCream),

            horizontalAlignment = Alignment.CenterHorizontally
        )

        {
            Box(modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .background(Color.Transparent),
                contentAlignment = Alignment.Center

            ){
           Text(text = "Hi, Welcome Back",
               color = Color.Black,
               fontSize = 17.sp,
               fontWeight = FontWeight.Bold)
            }
            MoneyOverviewBox()

        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(
                    RoundedCornerShape(
                        topStart = 36.dp,
                        topEnd = 36.dp,
                        bottomEnd = 0.dp,
                        bottomStart = 0.dp,
                    ))
            .background(VanillaLight),

            contentAlignment = Alignment.Center

        ) {
            Column(
                modifier = Modifier

                    .fillMaxHeight(0.7f)

                    .padding(16.dp)
                    .background(VanillaLight)



            ) {
                Text(
                    text = "Recent transactions",
                    color = Color.Black,

                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(recentTransactionList.orEmpty()) { transaction ->
                        ElevatedCard(
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 0.dp
                            ),
                            colors = CardDefaults.cardColors(
                                containerColor = VanillaLight,
                                contentColor = VanillaLight
                            ),

                            modifier = Modifier
                                .fillMaxWidth()
                                .height(90.dp)

                                .padding(6.dp),

                            shape = RoundedCornerShape(8.dp),

                            ) {
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                horizontalArrangement = Arrangement.SpaceAround,
                                verticalAlignment = Alignment.CenterVertically,

                                ) {
                                Box(
                                    modifier = Modifier
                                        .size(35.dp)
                                        .clip(RoundedCornerShape(18.dp))

                                ) {
                                    Image(
                                        painter = painterResource(id = if (transaction.type == "income") R.drawable.trending_up else R.drawable.trending_down),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(20.dp),
                                        contentScale = ContentScale.Fit,
                                        colorFilter = ColorFilter.tint(if (transaction.type == "income") Color.Green else Color.Red)
                                    )
                                }
                                VerticalLine()
                                Text(
                                    transaction.category,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Black,

                                    fontWeight = FontWeight.Medium,
                                    fontSize = with(LocalConfiguration.current) {
                                        when (smallestScreenWidthDp) {
                                            in 0..600 -> 12.sp // Small screens
                                            in 601..720 -> 14.sp // Medium screens
                                            else -> 16.sp // Large screens
                                        }
                                    }
                                )
                                VerticalLine()
                                Text(
                                    text =  if (transaction.type == "income") "₹${transaction.amount}" else "-₹${transaction.amount}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = if (transaction.type == "income") Color.Black else SkyBlue,
                                    fontWeight = FontWeight.Medium,

                                    fontSize = 13.sp,
                                )

                            }
                        }
                    }
                }
            }

//            Box(
//                modifier = Modifier.fillMaxSize()
//                    .background(color = Color.Black)
//            ) {
//                // Your content here
//
//                Column(
//                    modifier = Modifier
//                        .padding(bottom = 64.dp)
//                        .align(Alignment.BottomEnd)
//                ) {
//                    Spacer(modifier = Modifier.weight(1f)) // Pushes the FloatingActionButton above the BottomNavigationBar
//                    FloatingActionButton(
//                        onClick = { navController.navigate("add_amount_screen") },
//                        containerColor = MaterialTheme.colorScheme.background,
//                        modifier = Modifier.padding(16.dp),
//                        shape = CircleShape
//                    ) {
//                        Icon(Icons.Filled.Add, "Floating action button.")
//                    }
//                }
//            }


        }

    }
}

