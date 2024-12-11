package com.example.trivia

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

const val QUESTION_EXTRA = "QuestionExtra"
const val T = "TESTTEST"
class QuestionAdapter (private val context: Context, private val questions: List<Question>) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }
        val point : TextView = itemView.findViewById<TextView>(R.id.point)
        val cat : TextView = itemView.findViewById<TextView>(R.id.Cat)
        override fun onClick(p0: View?) {
            Log.d(T, "clicked")
            val q2: Question = questions[absoluteAdapterPosition]
            val intent = Intent(context, DetailActivity::class.java)
            val jsonStr = Json.encodeToString(q2)
            intent.putExtra(QUESTION_EXTRA, jsonStr)
            context.startActivity(intent)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //Log.d("TESTTEST", "9" + questions.toString())
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.question, parent, false)
        return ViewHolder(contactView)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val q2: Question = questions.get(position)
        holder.point.text = q2.point.toString()
        holder.cat.text = q2.cat
    }
    override fun getItemCount() = questions.size

}