package com.example.money_management_app.screens.addamount

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpense(navController: NavHostController) {
    val viewModel: FinanceViewModel = viewModel()
    var amountExpense by remember {
        mutableIntStateOf(
            0
        )
    }
    val list = listOf("Food", "Beauty", "Bills", "Clothing", "Education", "Health")
    var selectedText by remember {
        mutableStateOf(list[0])
    }
    var isExpanded by remember {
        mutableStateOf(false)
    }
    val coroutineScope = rememberCoroutineScope()







    ElevatedCard(

        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White, //Card background color
            contentColor = Color.White  //Card content color,e.g.text
        ),
        modifier = Modifier
            .height(400.dp)
            .width(330.dp),
        shape = RoundedCornerShape(8.dp),

        ) {
        Column(
            modifier = Modifier
                .height(400.dp)
                .width(330.dp),

            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//        Text(text = "Amount")
            OutlinedTextField(
                label = { Text("Enter Amount", style = TextStyle(fontSize = 10.sp)) },
                value = amountExpense.toString(),
                onValueChange = { text ->
                    amountExpense = text.toIntOrNull() ?: 0
                },
                textStyle = TextStyle(fontSize = 12.sp),
                modifier = Modifier
                    .width(250.dp)
                    .height(55.dp)


            )
            Text(
                text = "Category",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                fontSize = 10.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 7.dp) // Add padding and align to start
                    .fillMaxWidth(), // Fill the width of the column

            )

            ExposedDropdownMenuBox(expanded = isExpanded,

                onExpandedChange = { isExpanded = !isExpanded })

            {
                TextField(
                    modifier = Modifier

                        .width(300.dp)
                        .height(45.dp)

                        .menuAnchor(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ExposedDropdownMenuDefaults.textFieldColors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        //unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.Transparent
                    ),

                    value = selectedText,
                    onValueChange = {},
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                    textStyle = TextStyle(fontSize = 12.sp)

                )



                ExposedDropdownMenu(
                    expanded = isExpanded,

                    onDismissRequest = { isExpanded = false },
                )

                {
                    list.forEachIndexed { index, text ->

                        DropdownMenuItem(
                            text = { Text(text = text) },
                            onClick = {
                                selectedText = list[index]
                                isExpanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding

                        )

                    }

                }


            }

            Button(
                onClick = {
                    coroutineScope.launch {
                        try {
                            viewModel.addExpense(amountExpense, selectedText)
                            viewModel.addRecentTransaction(amountExpense,selectedText)
                        } finally {
                            navController.navigate("home_screen")
                        }

                    }
                },

                modifier = Modifier
                    .height(40.dp)
                    .width(150.dp),
                colors = ButtonDefaults.buttonColors(Color.Black)

            ) {
                Text(
                    text = "Add Expense",
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 10.sp
                )
            }

        }
    }
}