package com.example.project6bitfit2

import android.app.Application

class foodApplication : Application() {
    val db by lazy { foodDatabase.getInstance(this)
    }
}