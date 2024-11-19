package com.example.project6bitfit2

import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project6bitfit2.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar

private val tag = "MainActivity"
class MainActivity : AppCompatActivity() {
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content, fragment, null)
        fragmentTransaction.commit()
    }
    //private var foodList = mutableListOf<food>()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        getWindow().setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        replaceFragment(foodFragment())
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_log -> fragment = foodFragment()
                R.id.nav_dash -> fragment = dashFragment()
            }
            replaceFragment(fragment)
            true
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}



        /*
        var input : String
        var input2 : String
        val one = foodAdapter(foodList)
        val list = findViewById<RecyclerView>(R.id.list)
        val ave = findViewById<TextView>(R.id.Average)
        var count = 0
        list.adapter = one
        list.layoutManager = LinearLayoutManager(this)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        //val view = binding.root
        //setContentView(view)
        findViewById<Button>(R.id.submit).setOnClickListener() {
            input = findViewById<TextView>(R.id.Input).text.toString()
            input2 = findViewById<TextView>(R.id.Input2).text.toString()
            lifecycleScope.launch(IO) {
                (application as foodApplication).db.foodDao().insert(foodEntity(name = input, calAmount = input2))}
            lifecycleScope.launch {
                (application as foodApplication).db.foodDao().getAll().collect { databaseList ->
                    databaseList.map { entity ->
                        food(
                            entity.name,
                            entity.calAmount
                        )
                    }.also { mappedList ->
                        foodList.clear()
                        foodList.addAll(mappedList)
                        one.notifyDataSetChanged()
                    }
                }
            }
            if (foodList.size > 0) {
                count = 0
                for (i in foodList) {
                    count = count + i.calAmount.toInt()
                }
                ave.text = "Average: " + (count / foodList.size).toString()
                one.notifyDataSetChanged()
            }
        }
        findViewById<Button>(R.id.Clear).setOnClickListener() {
            lifecycleScope.launch(IO) {
                (application as foodApplication).db.foodDao().deleteAll()
            }
            foodList.clear()
            one.notifyDataSetChanged()
            ave.text="Average: 0"
        }*/