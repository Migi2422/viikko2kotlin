package com.example.viikko1.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.viikko1.domain.Task
import com.example.viikko1.domain.mockTasks

class TaskViewModel : ViewModel() {

    // Aktiivinen lista UI:lle
    var tasks by mutableStateOf(listOf<Task>())
        private set

    // Alkuperäinen lista suodatuksen palauttamiseen
    private var originalTasks = listOf<Task>()

    init {
        tasks = mockTasks
        originalTasks = mockTasks
    }

    fun addTask(task: Task) {
        originalTasks = originalTasks + task
        tasks = originalTasks
    }

    fun toggleDone(id: Int) {
        originalTasks = originalTasks.map { t ->
            if (t.id == id) t.copy(done = !t.done) else t
        }
        tasks = originalTasks
    }

    fun removeTask(id: Int) {
        originalTasks = originalTasks.filter { it.id != id }
        tasks = originalTasks
    }

    fun filterByDone(done: Boolean) {
        tasks = originalTasks.filter { it.done == done }
    }

    fun sortByDueDate() {
        tasks = originalTasks.sortedBy { it.dueDate }
    }

    fun resetFilter() {
        tasks = originalTasks
    }
}
