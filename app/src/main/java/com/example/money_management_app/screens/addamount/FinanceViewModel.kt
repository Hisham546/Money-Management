package com.example.money_management_app.screens.addamount

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.money_management_app.room.Expense
import com.example.money_management_app.room.Income
import com.example.money_management_app.room.MyApp
import com.example.money_management_app.room.RecentTransactions
import kotlinx.coroutines.launch

class FinanceViewModel : ViewModel() {
    private val financeDao = MyApp.database.financeDao()

    val addedIncome = MutableLiveData<Income?>()
    val addedExpense = MutableLiveData<Expense?>()
    val _transactionHistory = MutableLiveData<List<RecentTransactions>>()
    val transactionHistory: LiveData<List<RecentTransactions>> = _transactionHistory

    //Income operations
    suspend fun addIncome(income: Int, category: String) {
        val checkDataExisting = MyApp.database.financeDao().fetchIncome()
        if (checkDataExisting != null) {
            val totalIncome = checkDataExisting.income + income
            MyApp.database.financeDao().updateIncome(1, totalIncome, category)
        } else {


            val data = Income(income = income, category = category)
            MyApp.database.financeDao().insertIncome(data)


        }
    }

    fun fetchIncome() {
        viewModelScope.launch {
            addedIncome.value = financeDao.fetchIncome()
        }
    }


    //Expense operations
    suspend fun addExpense(expense: Int, category: String) {
        val currentExpense = MyApp.database.financeDao().fetchExpense()

        if (currentExpense != null) {
            val newTotalExpense = currentExpense.expense + expense

            MyApp.database.financeDao().updateExpense(1, newTotalExpense, category)
        } else {


            val data = Expense(expense = expense, category = category)
            MyApp.database.financeDao().insertExpense(data)


        }
    }

    fun fetchExpense() {
        viewModelScope.launch {
            addedExpense.value = financeDao.fetchExpense()
        }
    }


    //transaction history operations

    suspend fun addRecentTransaction(amount: Int, category: String, type: String) {

        val newTransaction = RecentTransactions(amount = amount, category = category, type = type)
        val currentList = _transactionHistory.value.orEmpty().toMutableList()
        currentList.add(0, newTransaction) // Add the new transaction at the beginning of the list
        Log.wtf("currentLis..........", "Current list: $currentList")
        _transactionHistory.value = currentList
        MyApp.database.financeDao().insertRecentTransaction(newTransaction)
    }

    fun fetchRecentTransaction() {
        viewModelScope.launch {
            _transactionHistory.value = financeDao.getAllRecentTransactions()

        }

    }
}