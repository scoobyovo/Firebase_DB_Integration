package com.example.challenge6_firebase

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.challenge6_firebase.ui.theme.Challenge6_FirebaseTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Challenge6_FirebaseTheme {
                val db = Firebase.firestore
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AddToDo(mod = Modifier, db = Firebase)
                    ToDoList(mod = Modifier, db = Firebase)
                }
            }
        }
    }
}

@Composable
fun AddToDo(mod : Modifier, db : Firebase)
{
    val task_input = remember {mutableStateOf("")}

    Column {
        TextField(
            modifier = mod,
            placeholder = {Text("Enter task...")},
            label = {Text("New task..")},
            value = task_input.value,
            onValueChange = { newText -> task_input.value = newText}
        )
        Button(
            modifier = Modifier,
            onClick = { AddTask(db.firestore, task_input.value) }
        ) {
            Text("Add task")
            //Log.i("TASK ADDED", "task = ${task_input.value}")
        }
    }
}

@Composable
fun ToDoList(mod : Modifier, db : Firebase)
{
    val tasks = mutableListOf<TaskModel>()
    val todoCollection = db.firestore.collection("todo")

    todoCollection
        .get()
        .addOnSuccessListener { documents ->
            documents.map {
                documentSnapshot ->
                    val dataMap = documentSnapshot.data
                    val task = TaskModel(completed = (dataMap["completed"] as Boolean),
                        dataMap["task"] as String
                    )
                    Log.i("TASK INFO", task.task)
                    tasks.add(task)
            }
        }

    LazyColumn {
        items(tasks) {task->
            Log.i("TaskModel creatred", "COmpleted = ${task.completed}, task = ${task.task}")
            Task(task)
        }
    }
}

