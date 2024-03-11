package com.example.money_management_app.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Income :: class,Expense ::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract  fun financeDao() : FinanceDao
}