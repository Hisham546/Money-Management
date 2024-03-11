package com.example.money_management_app.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface FinanceDao {

    @Insert
    suspend fun insertIncome(data: Income)

    @Query("SELECT * FROM IncomeDetails LIMIT 1")
    suspend fun fetchIncome(): Income?

    //
    @Query("UPDATE IncomeDetails SET income = :income, category = :category WHERE id = :id")
    // @Update
    suspend fun updateIncome(id: Int, income: Int, category: String)

    @Delete
    suspend fun deleteIncome(data: Income)

    @Insert
    suspend fun insertExpense(data: Expense)

    @Query("SELECT * FROM ExpenseDetails LIMIT 1")
    suspend fun fetchExpense(): Expense?
}
