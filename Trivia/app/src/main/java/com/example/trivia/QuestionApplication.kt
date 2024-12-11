package com.example.trivia


import android.app.Application

class QuestionApplication : Application() {
    val db by lazy { QuestionDatabase.getInstance(this)
    }
}