package com.espressotestcases.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.espressotestcases.R
import com.espressotestcases.adapter.RecyclerAdapter
import com.espressotestcases.UserData
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //getIntent data
        val welcomeMessage = intent.getStringExtra("welcomeMessage")
        welcomeText.text = welcomeMessage


        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)


        val adapter =
            RecyclerAdapter(this, UserData.getData())

        //now adding the adapter to recyclerview
        recyclerView.adapter = adapter


    }
}
