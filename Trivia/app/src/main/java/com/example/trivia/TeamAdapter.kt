package com.example.trivia

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TeamAdapter  (private val context: Context, private val teams: List<Team>)
    : RecyclerView.Adapter<TeamAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val plus = itemView.findViewById<Button>(R.id.Plus)
        val minus = itemView.findViewById<Button>(R.id.Minus)
        val name = itemView.findViewById<TextView>(R.id.TeamName)
        val point = itemView.findViewById<TextView>(R.id.TeamPoint)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.team, parent, false)
        return ViewHolder(contactView)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val t2: Team = teams.get(position)
        holder.name.text = position.toString()
        holder.point.text = t2.points.toString()
        holder.plus.setOnClickListener() {
            t2.points = t2.points + 100
            holder.point.text = t2.points.toString()
        }
        holder.minus.setOnClickListener() {
            t2.points = t2.points - 100
            holder.point.text = t2.points.toString()
        }
    }
    override fun getItemCount() = teams.size
}