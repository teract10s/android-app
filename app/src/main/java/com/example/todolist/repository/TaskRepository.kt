package com.example.todolist.repository

import com.example.todolist.model.Task

class TaskRepository {
    private var tasks: List<Task> = listOf()

    fun addTask(task: Task) {
        tasks = tasks + task
    }

    fun getNames(): List<String> {
        return tasks.map(Task::name)
    }

    fun getTasks(): List<Task> {
        return tasks
    }
}