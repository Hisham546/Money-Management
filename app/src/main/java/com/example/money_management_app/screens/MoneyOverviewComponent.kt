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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.money_management_app.room.Income
import com.example.money_management_app.room.MyApp
import com.example.money_management_app.ui.theme.BlackShade

@Composable
fun  MoneyOverviewBox(){

    var addedIncome by remember { mutableStateOf<Income?>(null) }

    LaunchedEffect(key1 = true) {

//retrieve the data
        addedIncome =  MyApp.database.financeDao().fetchIncome()
        Log.d(addedIncome.toString(),"............")
    }
    Column (modifier = Modifier
        .fillMaxHeight(0.5f)
        .fillMaxWidth()
        .background(Color.White)
    ){

        Row (modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .background(BlackShade),
                horizontalArrangement = Arrangement.SpaceEvenly

        ){
            Box(modifier = Modifier
                .height(90.dp)
                .width(140.dp)
                .background(Color.White, shape = RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center




            ){
                //Arrangement.SpaceEvenly will give space for contents inside ,Alignment.CenterHorizontally will set contents to center
                Column (verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()){


                    Text(
                        text = "Income",
                        modifier = Modifier,
                    )


                    Text(
                        text = "${addedIncome?.income ?: "0:00"}",
                        modifier = Modifier
                    )
                }
            }
            Box(modifier = Modifier
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
                    Text(text = "Expense")
                }
            }
        }
    }
}