package com.example.challenge6_firebase

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore


fun AddTask(db : FirebaseFirestore, task : String)
{
    val new_task = hashMapOf("task" to task, "completed" to false)
    db.collection("todo").add(new_task)
        .addOnSuccessListener { documentReference ->
            Log.i("KS", "Task added to database -  $task")
        }
        .addOnFailureListener { documentReference->
            Log.i("KS-ERROR", "Unable to add task to database - $task")
        }

}

fun DeleteTask(db : Firebase, task_id : Int)
{

}

fun UpdateTask(){

}

