package com.example.todolist.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import com.example.todolist.R
import com.example.todolist.model.Task
import com.example.todolist.repository.TaskRepository
import java.util.Calendar

class CreateTaskActivity : ComponentActivity() {
    private lateinit var name: String
    private lateinit var edtTaskName: EditText
    private lateinit var edtTaskDescription: EditText
    private lateinit var datePicker: DatePicker
    private lateinit var timePicker: TimePicker
    private val taskRepository: TaskRepository = TaskRepository()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)
        name = intent.getStringExtra("name").toString()

        edtTaskName = findViewById(R.id.edtTaskName)
        edtTaskName.setText(name)

        edtTaskDescription = findViewById(R.id.edtTaskDescription)
        datePicker = findViewById(R.id.datePicker)
        timePicker = findViewById(R.id.timePicker)

        val btnCreateTask: Button = findViewById(R.id.btnCreateTask)
        btnCreateTask.setOnClickListener {
            createTask()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createTask() {
        val day = datePicker.dayOfMonth
        val month = datePicker.month
        val year = datePicker.year

        val hour = timePicker.hour
        val minute = timePicker.minute

        val calendar = Calendar.getInstance()
        calendar.set(year, month, day, hour, minute)

        val descriptionInput: EditText = findViewById(R.id.edtTaskDescription)
        val description: String = descriptionInput.text.toString()

        val nameInput: EditText = findViewById(R.id.edtTaskName)
        val name: String = nameInput.text.toString()

        val task = Task(name, description, calendar)
        taskRepository.addTask(task)
        val resultIntent = Intent()
        resultIntent.putExtra("task", name)
        setResult(RESULT_OK, resultIntent)
        finish()
    }
}
