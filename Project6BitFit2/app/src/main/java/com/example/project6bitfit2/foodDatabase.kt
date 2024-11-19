package com.example.project6bitfit2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [foodEntity::class], version = 2)
abstract class foodDatabase : RoomDatabase(){
    abstract fun foodDao(): foodDao

    companion object {

        @Volatile
        private var INSTANCE: foodDatabase? = null

        fun getInstance(context: Context): foodDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                foodDatabase::class.java, "food-db"

            )
                .fallbackToDestructiveMigration()
                .build()
    }
}