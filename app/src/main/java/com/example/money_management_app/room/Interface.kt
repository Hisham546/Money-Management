package com.example.money_management_app.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface FinanceDao {
    //income operations
    @Insert
    suspend fun insertIncome(data: Income)

    @Query("SELECT * FROM IncomeDetails LIMIT 1")
    suspend fun fetchIncome(): Income?

    //
    @Query("UPDATE IncomeDetails SET income = :income, category = :category WHERE id = :id")

    suspend fun updateIncome(id: Int, income: Int, category: String)

    @Delete
    suspend fun deleteIncome(data: Income)

    //expense operations
    @Insert
    suspend fun insertExpense(data: Expense)

    @Query("SELECT * FROM ExpenseDetails LIMIT 1")
    suspend fun fetchExpense(): Expense?

    //transaction history operations
    @Insert
    suspend fun insertRecentTransaction(data: RecentTransactions)

    @Query("SELECT * FROM TransactionHistoryDetails")
    suspend fun getAllRecentTransactions(): List<RecentTransactions>
}
