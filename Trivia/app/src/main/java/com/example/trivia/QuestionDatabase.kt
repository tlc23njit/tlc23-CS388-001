package com.example.trivia

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [QuestionEntity::class], version = 3)
abstract class QuestionDatabase : RoomDatabase(){
    abstract fun QuestionDao(): QuestionDao

    companion object {

        @Volatile
        private var INSTANCE: QuestionDatabase? = null

        fun getInstance(context: Context): QuestionDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                QuestionDatabase::class.java, "food-db"
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}