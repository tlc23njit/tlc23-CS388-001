package com.example.trivia

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
class QuestionFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_question, container, false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        insertQ(view)
    }
    private fun insertQ(view: View) {
        var q : String
        var a : String
        var c : String
        var p : String
        view.findViewById<Button>(R.id.Submit).setOnClickListener() {
            q = view.findViewById<TextView>(R.id.Q2).text.toString()
            a = view.findViewById<TextView>(R.id.A2).text.toString()
            c = view.findViewById<TextView>(R.id.C2).text.toString()
            p = view.findViewById<TextView>(R.id.P2).text.toString()
            lifecycleScope.launch(IO) {
                (activity?.application as QuestionApplication).db.QuestionDao().insert(
                    QuestionEntity(
                        q=q,
                        a=a,
                        point=p.toInt(),
                        cat=c)
                )
            }
            view.findViewById<TextView>(R.id.Q2).text = ""
            view.findViewById<TextView>(R.id.A2).text= ""
            view.findViewById<TextView>(R.id.C2).text= ""
            view.findViewById<TextView>(R.id.P2).text= ""
            Toast.makeText(view.context, "Submitted", Toast.LENGTH_SHORT).show()
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(): Fragment {
            val fragment = Fragment()
            return fragment
        }
    }
}