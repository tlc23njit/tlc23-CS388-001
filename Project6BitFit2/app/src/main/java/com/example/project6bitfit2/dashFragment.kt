package com.example.project6bitfit2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class dashFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dash, container, false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchAverage(view)
    }
    private fun fetchAverage(view: View) {
        val min = view.findViewById<TextView>(R.id.min)
        val max = view.findViewById<TextView>(R.id.max)
        val ave = view.findViewById<TextView>(R.id.ave)
        lifecycleScope.launch() {
            //val min2: Int
            //val max2: Int
            //val ave2: Double
            (activity?.application as foodApplication).db.foodDao().getAve().collect {
                item -> min.text = "Average Calories: " + item.toString()
            }
        }
        lifecycleScope.launch() {
            (activity?.application as foodApplication).db.foodDao().getMin().collect {
                    item -> max.text = "Minimum Calories: " + item.toString()
            }
        }
        lifecycleScope.launch() {
            (activity?.application as foodApplication).db.foodDao().getMax().collect {
                    item -> ave.text = "Maximum Calories: " + item.toString()
            }
        }
    }
    companion object {
        fun newInstance(): foodFragment {
            return foodFragment()
        }
    }
}