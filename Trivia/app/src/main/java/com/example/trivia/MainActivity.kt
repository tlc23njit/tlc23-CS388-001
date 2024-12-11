package com.example.trivia

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content, fragment, null)
        fragmentTransaction.commit()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val gameStart = findViewById<Button>(R.id.GameStart)
        val question = findViewById<Button>(R.id.AddQuestion)
        val back = findViewById<Button>(R.id.Back)
        gameStart.setOnClickListener() {
            gameStart.visibility = View.INVISIBLE
            question.visibility = View.INVISIBLE
            gameStart.setClickable(false)
            question.setClickable(false)
            back.visibility = View.INVISIBLE
            back.setClickable(false)
            replaceFragment(GameFragment())

        }
        question.setOnClickListener() {
            gameStart.visibility = View.INVISIBLE
            question.visibility = View.INVISIBLE
            gameStart.setClickable(false)
            question.setClickable(false)
            replaceFragment(QuestionFragment())
        }
        back.setOnClickListener() {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}