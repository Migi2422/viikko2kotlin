package com.example.viikko1.homeScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikko1.domain.Task
import com.example.viikko1.viewmodel.TaskViewModel
import java.time.LocalDate




@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: TaskViewModel = viewModel()
) {
    val tasks = viewModel.tasks
    val today = LocalDate.now().toString()

    var newTaskTitle by remember { mutableStateOf("") }

    Column(modifier.padding(16.dp)) {

        Text("Task list")


        TextField(
            value = newTaskTitle,
            onValueChange = { newTaskTitle = it },
            label = { Text("New task title") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = {
                if (newTaskTitle.isNotBlank()) {
                    val newId = (tasks.maxOfOrNull { it.id } ?: 0) + 1
                    viewModel.addTask(
                        Task(
                            id = newId,
                            title = newTaskTitle,
                            description = "",
                            priority = 1,
                            dueDate = today,
                            done = false
                        )
                    )
                    newTaskTitle = ""
                }
            }
        ) {
            Text("Add task")
        }

        Spacer(Modifier.height(16.dp))

        // Sort-nappi
        Button(onClick = { viewModel.sortByDueDate() }) {
            Text("Sort by due date")
        }

        Spacer(Modifier.height(8.dp))

        // Filter-nappi
        Button(onClick = { viewModel.filterByDone(true) }) {
            Text("Show only done tasks")
        }
        Button(onClick = { viewModel.resetFilter() }) {
            Text("Show all tasks")
        }

        Spacer(Modifier.height(16.dp))

        // Tehtävälista
        LazyColumn {
            items(tasks) { task ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Checkbox done-tilalle
                    Checkbox(
                        checked = task.done,
                        onCheckedChange = { viewModel.toggleDone(task.id) }
                    )

                    // Tehtävän nimi
                    Text(
                        text = "${task.title} (${task.dueDate})",
                        modifier = Modifier.weight(1f)
                    )


                    // Poista-nappi
                    Button(onClick = { viewModel.removeTask(task.id) }) {
                        Text("Delete")
                    }


                }
            }
        }
    }
}

