package com.kodotagi.protolist

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var addButton: Button
    private lateinit var taskEditText: EditText
    private lateinit var tasksLayout: LinearLayout
    private val taskList = ArrayList<String>()
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("task_preferences", Context.MODE_PRIVATE)

        addButton = findViewById(R.id.addButton)
        taskEditText = findViewById(R.id.taskEditText)
        tasksLayout = findViewById(R.id.tasksLayout)

        addButton.setOnClickListener {
            val taskText = taskEditText.text.toString()
            addTask(taskText)
            taskList.add(taskText)
            saveTaskList()
            taskEditText.text.clear()
        }

        loadTaskList()
    }

    private fun loadTaskList() {
        val savedTasks = sharedPreferences.getStringSet("TASK_LIST", HashSet())
        savedTasks?.forEach { taskText ->
            addTask(taskText)
            taskList.add(taskText)
        }
    }

    private fun saveTaskList() {
        val editor = sharedPreferences.edit()
        editor.putStringSet("TASK_LIST", HashSet(taskList))
        editor.apply()
    }

    private fun addTask(taskText: String) {
        val taskView = layoutInflater.inflate(R.layout.task_item, null)
        val taskTextView = taskView.findViewById<TextView>(R.id.taskTextView)
        taskTextView.text = taskText

        val deleteButton = taskView.findViewById<ImageButton>(R.id.deleteButton)
        deleteButton.setOnClickListener {
            tasksLayout.removeView(taskView)
            taskList.remove(taskText)
            saveTaskList()
        }

        tasksLayout.addView(taskView)
    }
}


