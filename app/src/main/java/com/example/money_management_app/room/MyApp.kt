package com.example.money_management_app.room

import android.app.Application
import androidx.room.Room

class MyApp : Application() {
    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"

        ).fallbackToDestructiveMigration()
//            .allowMainThreadQueries()
            .build()
    }
}