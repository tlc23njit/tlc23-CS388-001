package com.example.trivia

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match

/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
const val T3 = "TESTTEST"
class GameFragment : Fragment() {
    private var questions = mutableListOf<Question>()
    private lateinit var qRV: RecyclerView
    private lateinit var qA: QuestionAdapter
    private lateinit var tRV: RecyclerView
    private lateinit var tA: TeamAdapter
    private var teams = mutableListOf<Team>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)
        val layoutManager = LinearLayoutManager(context)
        qRV = view.findViewById(R.id.QuestionList)
        tRV = view.findViewById(R.id.Points)
        tRV.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL ,false)
        qA = QuestionAdapter(view.context, questions)
        qRV.layoutManager = GridLayoutManager(view.context, 3)
        qRV.adapter = qA
        tA = TeamAdapter(view.context, teams)
        tRV.adapter = tA
        // Inflate the layout for this fragment
        qA.notifyDataSetChanged()
        tA.notifyDataSetChanged()
        getQs(view)
        qA.notifyDataSetChanged()
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getQs(view)
        getTs(view)
        Log.d(T3, "1" + questions.toString())
        qA.notifyDataSetChanged()
        Log.d(T3, "2" + questions.toString())
        tA.notifyDataSetChanged()
    }
    private fun getQs(view: View) {
        lifecycleScope.launch {
            (activity?.application as QuestionApplication).db.QuestionDao().getAll()
                .collect { dblist ->
                    dblist.map { entity ->
                        Question(
                            entity.q,
                            entity.a,
                            entity.point,
                            entity.cat,
                        )
                    }.also{ mappedList ->
                            Log.d(T3,"7" + questions.toString())
                            questions.addAll(mappedList)
                            questions = questions.distinct().toMutableList()
                            Log.d(T3, "4" + mappedList.toString())
                            qA.notifyDataSetChanged()
                            Log.d(T3, "5" + questions.toString())
                        }
                }
        }
        Log.d(T3, "3" + questions.toString())
    }
    private fun getTs(view: View) {
        teams.add(Team(0))
        teams.add(Team(0))
        teams.add(Team(0))
        teams.add(Team(0))
        tA.notifyDataSetChanged()
        //Log.d("TESTTEST", "6" + teams.toString())
    }

    companion object {
        @JvmStatic
        fun newInstance(): Fragment {
            val fragment = Fragment()
            return fragment
        }
    }
}