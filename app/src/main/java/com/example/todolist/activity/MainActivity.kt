package com.example.todolist.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolist.R
import com.example.todolist.repository.TaskRepository
import com.example.todolist.ui.theme.ToDoListTheme

class MainActivity : ComponentActivity() {
    private lateinit var itemsAdapter: ArrayAdapter<String>
    private lateinit var list: ListView
    private lateinit var items: List<String>
    private lateinit var button: Button
    private val taskRepository: TaskRepository = TaskRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list = findViewById(R.id.list)
        button = findViewById(R.id.button)

        button.setOnClickListener {
            addItem(it)
        }

        items = mutableListOf()
        itemsAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        list.adapter = itemsAdapter

        taskRepository.getTasks().forEach { t ->
            itemsAdapter.add(t.name)
        }
    }

    private fun addItem(view: View) {
        val input: EditText = findViewById(R.id.edit_text)
        val item: String = input.text.toString()
        if (item != "") {
            val intent = Intent(this, CreateTaskActivity::class.java)
            intent.putExtra("name", item)
            input.setText("")
            startActivity(intent)
            itemsAdapter.add(intent.getStringExtra("name").toString())
        } else {
            Toast.makeText(applicationContext, "Please enter correct task", Toast.LENGTH_LONG)
                .show()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ToDoListTheme {
        Greeting("To do List")
    }
}