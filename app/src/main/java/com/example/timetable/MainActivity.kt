package com.example.timetable

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    // Define variables to save state
    private var todos: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listView) // Create object (view) ListView - a class for displaying lists
        val userData: EditText = findViewById(R.id.userData)
        val button: Button = findViewById(R.id.buttonAdd)

        // Restore the state if there's saved instance state
        savedInstanceState?.let {
            todos = it.getStringArrayList("todos")?.toMutableList() ?: mutableListOf()
            todos?.let {
                val adapter = ArrayAdapter(this, R.layout.row, todos)
                listView.adapter = adapter
            }
        }

        val secondActButton: ImageButton = findViewById(R.id.buttonRight)
        secondActButton.setOnClickListener{
            val intent = Intent(this, Tuesday::class.java)
            startActivity(intent)
        }

        val adapter = ArrayAdapter(this, R.layout.row, todos)
        listView.adapter = adapter

        listView.setOnItemClickListener{adapterView, view, i, l ->
            val text = listView.getItemAtPosition(i).toString()
            adapter.remove(text)

            Toast.makeText(this, "You deleted: $text", Toast.LENGTH_SHORT).show()
        }

        button.setOnClickListener{
            var text = userData.text.toString().trim()
            if (text != "")
                adapter.insert(text, 0)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // Save the state
        outState.putStringArrayList("todos", ArrayList(todos))
    }
}

