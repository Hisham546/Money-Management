package com.example.money_management_app.screens

import android.graphics.drawable.Icon
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.money_management_app.room.MyApp
import com.example.money_management_app.room.Income
import com.example.money_management_app.ui.theme.BlackShade
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController) {
    var amount by remember {
        mutableIntStateOf(
            0
        )
    }
    val coroutineScope = rememberCoroutineScope()
    var addedIncome: Income? by remember { mutableStateOf(null) }


    // Function to add income
    suspend fun addIncome(income: Int) {
        val checkDataExisting = MyApp.database.financeDao().fetchIncome()
        if (checkDataExisting != null) {
            MyApp.database.financeDao().updateIncome(1, income)
        } else {

            Log.wtf("inside else", ".......else......")

            val data = Income(income = amount)
            MyApp.database.financeDao().insertIncome(data)


        }
    }


    LaunchedEffect(key1 = true) {

//retrieve the data
        addedIncome = MyApp.database.financeDao().fetchIncome()
//        Log.d(users.toString(), "sddddddddddddd")
    }

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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )

        {

            Text(text = "Money Management")
            OutlinedTextField(
                value = amount.toString(),
                onValueChange = { text ->
                    amount = text.toIntOrNull() ?: 0
                }, // Parse string to integer or default to 0
                modifier = Modifier
                    .width(250.dp)


            )
            Spacer(modifier = Modifier.height(15.dp))
            Button(
                onClick = {
                    coroutineScope.launch {
                        addIncome(amount)
                    }
                },

                modifier = Modifier
                    .height(40.dp)
                    .width(150.dp),
                colors = ButtonDefaults.buttonColors(Color.Gray)

            ) {
                Text(
                    text = "Add",
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 10.sp
                )
            }

//                Text(
//                    text = "Name: ${addedIncome?.income}",
//                    modifier = Modifier.padding(vertical = 8.dp)
//                )
            FloatingActionButton(onClick = { navController.navigate("add_amount_screen") }) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }

        }
    }

}