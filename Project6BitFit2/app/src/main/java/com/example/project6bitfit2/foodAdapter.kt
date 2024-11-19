package com.example.project6bitfit2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class foodAdapter(private val context: Context, private val foods: List<food>) :
    RecyclerView.Adapter<foodAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nameTextView: TextView = itemView.findViewById(R.id.title)
        val amountTextView: TextView = itemView.findViewById(R.id.amt)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.food, parent, false)
        return ViewHolder(contactView)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food2: food = foods.get(position)
        //Log.d("test3", water2.amount)
        holder.amountTextView.text = food2.calAmount
        //Log.d("test4", holder.amountTextView.text.toString())
        holder.nameTextView.text = food2.name
    }
    override fun getItemCount() = foods.size
}
