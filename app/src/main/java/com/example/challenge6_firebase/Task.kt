package com.example.challenge6_firebase

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.model.Document

@Composable
fun Task(task: TaskModel) : Unit
{

    Row(){
        Checkbox(checked = task.completed,
            onCheckedChange = {})
        Text(task.task)


    }
}
