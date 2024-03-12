package com.example.money_management_app.screens

import android.graphics.drawable.Icon
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.room.Transaction
import com.example.money_management_app.room.Expense
import com.example.money_management_app.room.MyApp
import com.example.money_management_app.room.Income
import com.example.money_management_app.screens.addamount.FinanceViewModel
import com.example.money_management_app.ui.theme.BlackShade
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel: FinanceViewModel = viewModel()



    val incomeRecord by viewModel.addedIncome.observeAsState()
    val expenseRecord by viewModel.addedExpense.observeAsState()

//    val transactionsList: List<Transaction> = (incomeRecord ?: emptyList<Income>()) + (expenseRecord ?: emptyList<Expense>())
//
//    LaunchedEffect(key1 = true) {
//
//
//        viewModel.fetchIncome()
//        viewModel.fetchExpense()
//
//
//    }

    Column(modifier = Modifier) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .background(BlackShade),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )

        {
            MoneyOverviewBox()

        }
        Box(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopStart) // Align the column to top start (top left)
                    .padding(16.dp)

            ) {
                Text(
                    text = "Recent transactions",
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 15.sp
                )

//                LazyColumn(
//                    modifier = Modifier.fillMaxSize()
//                ) {
//                    items(transactionsList) { transaction ->
//                        Column(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(16.dp)
//                        ) {
//                            // Display transaction details based on its type (Income or Expense)
//                            Text(
//                                text = if (transaction is Income) "Income: ${transaction.amount}" else "Expense: ${transaction.amount}",
//                                color = if (transaction is Income) Color.Green else Color.Red,
//                                fontWeight = FontWeight.Bold
//                            )
//                            Text(
//                                text = "Category: ${transaction.category}",
//                                color = Color.Black
//                            )
//
//                        }
//                    }
//                }
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomEnd) // Align the column to bottom end (bottom right)
                    .padding(16.dp)
            ) {
                FloatingActionButton(
                    onClick = { navController.navigate("add_amount_screen") },
                            containerColor = MaterialTheme.colorScheme.background,
                    modifier = Modifier.padding(16.dp),
                    shape = CircleShape,

                ) {
                    Icon(Icons.Filled.Add, "Floating action button.")
                }
            }
        }

    }
    }

