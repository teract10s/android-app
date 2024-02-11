package com.example.examsapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.list_view)
        val userData: EditText = findViewById(R.id.user_data)
        val addButton: Button = findViewById(R.id.add_button)

        val todos: MutableList<String> = mutableListOf()
        val adapter = ArrayAdapter(this, R.layout.list_item_layout, todos)
        listView.adapter = adapter

        listView.setOnItemClickListener { adapterView, view, i , l ->
            val text = listView.getItemAtPosition(i).toString()
            adapter.remove(text)
        }

        addButton.setOnClickListener() {
            val text = userData.text.toString().trim()
            if(text != ""){
                adapter.insert(text, 0)
            }
        }
    }
}
