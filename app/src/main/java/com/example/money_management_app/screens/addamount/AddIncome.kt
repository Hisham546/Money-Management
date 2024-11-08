package com.example.money_management_app.screens.addamount

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.money_management_app.room.Income
import com.example.money_management_app.room.MyApp
import com.example.money_management_app.ui.theme.VanillaBottom
import com.example.money_management_app.ui.theme.VanillaCream
import com.example.money_management_app.ui.theme.VanillaLight
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AddIncome(navController: NavHostController) {
    val viewModel: FinanceViewModel = viewModel()
    var amount by remember {
        mutableIntStateOf(
            0
        )
    }
    val list = listOf("Salary", "Savings", "card")
    var selectedText by remember {
        mutableStateOf(list[0])
    }
    var isExpanded by remember {
        mutableStateOf(false)
    }
    val coroutineScope = rememberCoroutineScope()

    val keyboardController = LocalSoftwareKeyboardController.current





    ElevatedCard(

        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = VanillaLight, //Card background color
            contentColor = Color.White  //Card content color,e.g.text
        ),
        modifier = Modifier
            .height(400.dp)
            .width(330.dp),
        shape = RoundedCornerShape(8.dp),

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                label = {
                    Text(
                        "Enter Amount",
                        style = TextStyle(fontSize = 10.sp, color = Color.Black)
                    )
                },
                value = amount.toString(),
                onValueChange = { text ->
                    amount = text.toIntOrNull() ?: 0
                },
                textStyle = TextStyle(fontSize = 12.sp, color = Color.Black),
                modifier = Modifier
                    .width(300.dp)
                    .height(55.dp)
                    .background(Color.Transparent),

                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = VanillaBottom,
                    unfocusedContainerColor = VanillaBottom,
                    disabledContainerColor = Color.Red

                )
            )
            Text(
                text = "Category",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                fontSize = 10.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 5.dp) // Add padding and align to start
                    .fillMaxWidth(), // Fill the width of the column

            )

            ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = { isExpanded = !isExpanded }
            ) {
                TextField(
                    modifier = Modifier
                        .width(300.dp)
                        .height(45.dp)
                        .menuAnchor()
                        .clickable {

                            keyboardController?.hide()

                        },
                    shape = RoundedCornerShape(8.dp),
                    colors = ExposedDropdownMenuDefaults.textFieldColors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = VanillaBottom,
                        unfocusedContainerColor = VanillaBottom,
                        disabledContainerColor = Color.White
                    ),
                    value = selectedText,
                    onValueChange = {},
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                    textStyle = TextStyle(fontSize = 12.sp, color = Color.Black)
                )

                ExposedDropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false },
                    modifier = Modifier
                        .background(VanillaBottom)
                ) {
                    list.forEachIndexed { index, text ->
                        DropdownMenuItem(
                            text = { Text(text = text,
                                fontSize = 12.sp,
                                color = Color.Black
                            ) },
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
                            viewModel.addIncome(amount, selectedText)
                            viewModel.addRecentTransaction(amount, selectedText, "income")
                        } finally {
                            navController.navigate("home_screen")
                        }
                    }
                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .height(50.dp)
                    .width(280.dp),
                colors = ButtonDefaults.buttonColors(VanillaCream)
            ) {
                Text(
                    text = "Add Income",
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp
                )
            }
        }
    }
}
