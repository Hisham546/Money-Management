package com.example.money_management_app.screens.addamount

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.money_management_app.room.Income
import com.example.money_management_app.room.MyApp
import kotlinx.coroutines.launch

class FinanceViewModel:ViewModel() {
    private val financeDao = MyApp.database.financeDao()
    val addedIncome = MutableLiveData<Income?>()
    suspend fun addIncome(income: Int) {
        val checkDataExisting = MyApp.database.financeDao().fetchIncome()
        if (checkDataExisting != null) {
            MyApp.database.financeDao().updateIncome(1, income)
        } else {

            Log.wtf("inside else", ".......else......")

            val data = Income(income = income)
            MyApp.database.financeDao().insertIncome(data)


        }
    }

    fun fetchIncome() {
        viewModelScope.launch {
            addedIncome.value = financeDao.fetchIncome()
        }
    }

}