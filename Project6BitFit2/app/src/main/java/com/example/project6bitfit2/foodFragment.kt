package com.example.project6bitfit2

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.compose.ui.window.application
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.project6bitfit2.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

private const val TAG = "foodFragment"
class foodFragment: Fragment() {
    private var foodList = mutableListOf<food>()
    private lateinit var foodRecyclerView: RecyclerView
    private lateinit var foodAdapter: foodAdapter
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_food, container, false)
        val layoutManager = LinearLayoutManager(context)
        foodRecyclerView = view.findViewById(R.id.list)
        foodRecyclerView.layoutManager = layoutManager
        foodRecyclerView.setHasFixedSize(true)
        foodAdapter = foodAdapter(view.context, foodList)
        foodRecyclerView.adapter = foodAdapter
        foodAdapter.notifyDataSetChanged()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchFood(view)
        foodAdapter.notifyDataSetChanged()
    }
    private fun fetchFood(view: View) {
        var input : String
        var input2 : String
        //val ave = view.findViewById<TextView>(R.id.Average)
        //var count = 0
        view.findViewById<Button>(R.id.submit).setOnClickListener() {
            input = view.findViewById<TextView>(R.id.Input).text.toString()
            input2 = view.findViewById<TextView>(R.id.Input2).text.toString()
            lifecycleScope.launch(IO) {
                (activity?.application as foodApplication).db.foodDao().insert(foodEntity(name = input, calAmount = input2))}
            lifecycleScope.launch {
                (activity?.application as foodApplication).db.foodDao().getAll().collect { databaseList ->
                    databaseList.map { entity ->
                        food(
                            entity.name,
                            entity.calAmount
                        )
                    }.also { mappedList ->
                        foodList.clear()
                        foodList.addAll(mappedList)
                        foodAdapter.notifyDataSetChanged()
                    }
                }
            }

        }
        view.findViewById<Button>(R.id.Clear).setOnClickListener() {
            lifecycleScope.launch(IO) {
                (activity?.application as foodApplication).db.foodDao().deleteAll()
            }
            foodList.clear()
            foodAdapter.notifyDataSetChanged()
            //ave.text="Average: 0"
        }
    }

    companion object {
        fun newInstance(): foodFragment {
            return foodFragment()
        }
    }
}