package com.example.trivia

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.serialization.json.Json


const val QUESTION_EXTRA2 = "QuestionExtra"
const val T2 = "TESTTEST"
class DetailActivity : AppCompatActivity() {
    private lateinit var q : TextView
    private lateinit var a : TextView
    private lateinit var p : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(T2, "detailactivity")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.questiondetail)
        val q2 = intent.getSerializableExtra(QUESTION_EXTRA2) //as Food?
        val decodedUser = Json.decodeFromString<Question>(q2.toString())
        val q3 = decodedUser
        q = findViewById<TextView>(R.id.Q)
        a = findViewById<TextView>(R.id.A)
        p = findViewById<TextView>(R.id.P)
        q.text = q3.q
        a.text = q3.a
        p.text = q3.point.toString()

    }
    fun screenTapped(view: View?) {
        a.visibility = View.VISIBLE
    }
}