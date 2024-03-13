package com.example.money_management_app.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.money_management_app.room.Income
import com.example.money_management_app.room.MyApp
import com.example.money_management_app.screens.addamount.FinanceViewModel
import com.example.money_management_app.ui.theme.BlackShade
import java.text.NumberFormat
import java.util.Locale


@Composable
fun MoneyOverviewBox() {
    val viewModel: FinanceViewModel = viewModel()
//    var addedIncome by remember { mutableStateOf<Income?>(null) }
    val incomeRecord by viewModel.addedIncome.observeAsState()
    val expenseRecord by viewModel.addedExpense.observeAsState()

    LaunchedEffect(key1 = true) {


        viewModel.fetchIncome()
        viewModel.fetchExpense()


    }

    // Function to format a number with commas
    fun formatNumberWithCommas(number: Int): String {
        val numberFormat = NumberFormat.getNumberInstance(Locale.getDefault())
        return numberFormat.format(number)
    }
    Column(
        modifier = Modifier
            .fillMaxHeight(0.5f)
            .fillMaxWidth()
            .background(BlackShade),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(BlackShade),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Box(
                modifier = Modifier
                    .height(90.dp)
                    .width(140.dp)
                    .background(Color.White, shape = RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center


            ) {
                //Arrangement.SpaceEvenly will give space for contents inside ,Alignment.CenterHorizontally will set contents to center
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {


                    Text(
                        text = "Income",
                        color = Color.Green,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        fontSize = 12.sp,
                    )


                    Text(
                        text = "₹${formatNumberWithCommas(incomeRecord?.income ?: 0)}",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        fontSize = 17.sp,

                        )
                }
            }
            Box(
                modifier = Modifier
                    .height(90.dp)
                    .width(140.dp)
                    .background(Color.White, shape = RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "Expense",

                        color = Color.Red,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        fontSize = 12.sp,
                    )
                    Text(
                        text = "₹${formatNumberWithCommas(expenseRecord?.expense ?: 0)}",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        fontSize = 17.sp,


                        )
                }
            }
        }
    }
}