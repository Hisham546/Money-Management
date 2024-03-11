package com.example.money_management_app.screens.addamount

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.money_management_app.room.Expense
import com.example.money_management_app.room.Income
import com.example.money_management_app.room.MyApp
import kotlinx.coroutines.launch

class FinanceViewModel:ViewModel() {
    private val financeDao = MyApp.database.financeDao()
    val addedIncome = MutableLiveData<Income?>()
    val addedExpense = MutableLiveData<Expense?>()
    suspend fun addIncome(income: Int,category:String) {
        val checkDataExisting = MyApp.database.financeDao().fetchIncome()
        if (checkDataExisting != null) {
            MyApp.database.financeDao().updateIncome(1, income,category)
        } else {

//            Log.wtf("inside else", ".......else......")

            val data = Income(income = income, category = category)
            MyApp.database.financeDao().insertIncome(data)


        }
    }

    fun fetchIncome() {
        viewModelScope.launch {
            addedIncome.value = financeDao.fetchIncome()
        }
    }

    suspend fun addExpense(expense: Int,category:String) {
//        val checkDataExisting = MyApp.database.financeDao().fetchIncome()
//        if (checkDataExisting != null) {
//            MyApp.database.financeDao().updateIncome(1, income,category)
//        } else {

          Log.wtf("inside addExpense", ".......else......")

            val data = Expense(expense = expense, category = category)
            MyApp.database.financeDao().insertExpense(data)


//        }
    }
    fun fetchExpense() {
        viewModelScope.launch {
            addedExpense.value = financeDao.fetchExpense()
        }
    }


}