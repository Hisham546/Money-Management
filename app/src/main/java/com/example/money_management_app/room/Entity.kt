package com.example.money_management_app.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "IncomeDetails")
data class Income(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val income: Int,
    val category: String

)

@Entity(tableName = "ExpenseDetails")
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val expense: Int,
    val category: String
)

@Entity(tableName = "TransactionHistoryDetails")
data class RecentTransactions(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val amount: Int,
    val category: String,
    val type:String

)