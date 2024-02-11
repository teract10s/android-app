package com.example.examsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Task(val title: String, val date: String)

class TaskAdapter(context: AppCompatActivity, private val resource: Int, private val tasks: MutableList<Task>) : ArrayAdapter<Task>(context, resource, tasks) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(resource, parent, false)
        val task = tasks[position]
        view.findViewById<TextView>(R.id.text_view).text = "${task.title} - ${task.date}"
        return view
    }
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.list_view)
        val userData: EditText = findViewById(R.id.user_data)
        val dateData: EditText = findViewById(R.id.date_data)
        val addButton: Button = findViewById(R.id.add_button)

        val todos: MutableList<Task> = mutableListOf()
        val adapter = TaskAdapter(this, R.layout.list_item_layout, todos)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            adapter.remove(adapter.getItem(position))
        }

        addButton.setOnClickListener {
            val text = userData.text.toString().trim()
            val date = dateData.text.toString().trim()
            if (text.isNotBlank() && date.isNotBlank()) {
                val task = Task(text, date)
                adapter.insert(task, 0)
                userData.text.clear()
                dateData.text.clear()
            }
        }
    }
}
