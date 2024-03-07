package com.example.money_management_app.screens.addamount

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.money_management_app.room.Income
import com.example.money_management_app.room.MyApp
import kotlinx.coroutines.launch

@Composable
fun AddIncome(){
    val viewModel: FinanceViewModel = viewModel()
    var amount by remember {
        mutableIntStateOf(
            0
        )
    }
    val coroutineScope = rememberCoroutineScope()







    Column (modifier = Modifier
        .height(150.dp)
        .width(330.dp),
//        .background(Color.Green),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
//        Text(text = "Amount")
        OutlinedTextField(
            value = amount.toString(),
            onValueChange = { text ->
                amount = text.toIntOrNull() ?: 0
            },
            modifier = Modifier
                .width(250.dp)
//                .height(26.dp)


        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            onClick = {
                coroutineScope.launch {
//                    addIncome(amount)
                    viewModel.addIncome(amount)
                }
            },

            modifier = Modifier
                .height(40.dp)
                .width(150.dp),
            colors = ButtonDefaults.buttonColors(Color.Black)

        ) {
            Text(
                text = "Add Income",
                color = Color.White,
                fontWeight = FontWeight.Medium,
                fontSize = 10.sp
            )
        }
    }
}