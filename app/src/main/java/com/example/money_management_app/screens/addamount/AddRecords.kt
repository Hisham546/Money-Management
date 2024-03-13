package com.example.money_management_app.screens.addamount

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.money_management_app.ui.theme.WhiteShade

@Composable
fun AddAmount(navController: NavHostController) {
    var showAddIncome by remember { mutableStateOf(true) }
    var showAddExpense by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Icon(Icons.Filled.ArrowBack, "",
                modifier = Modifier
                    .clickable { navController.navigate("home_screen") }
                    .padding(start = 30.dp))

            Text(
                text = "Add Record",
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 30.dp)
            )

        }
        Column(
            modifier = Modifier

                .fillMaxWidth()
                .height(35.dp),

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Row(
                modifier = Modifier

                    .width(330.dp)
                    .height(30.dp)
                    .background(
                        WhiteShade,
                        shape = RoundedCornerShape(5.dp)
                    ), //  text = "${addedIncome?.income ?: "0:00"}",
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Text(
                    text = "Income",
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                    modifier = Modifier.clickable {
                        showAddIncome = true
                        showAddExpense = false
                    }
                )
                Text(
                    text = "Expense",
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                    modifier = Modifier.clickable {
                        showAddExpense = true
                        showAddIncome = false
                    }
                )
            }
        }
        if (showAddIncome) {
            Column(
                modifier = Modifier
                    .height(450.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AddIncome(navController)
            }

        }
        if (showAddExpense) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AddExpense(navController)
            }
        }
    }
}