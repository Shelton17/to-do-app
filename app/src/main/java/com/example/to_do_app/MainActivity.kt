package com.example.to_do_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.to_do_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var taskAdapter: TaskAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val  view = binding.root
        setContentView(view)
        taskAdapter = TaskAdapter(mutableListOf())

        binding.rvToDoItems.adapter =  taskAdapter
        binding.rvToDoItems.layoutManager = LinearLayoutManager(this)

        binding.btnAddTask.setOnClickListener {
            val taskTitle = binding.etToDoTitle.text.toString()
            if (taskTitle.isNotEmpty()){
                val task = Task(taskTitle)
                taskAdapter.addTask(task)
                binding.etToDoTitle.text.clear()
            }
        }
        binding.btnDel.setOnClickListener {
            taskAdapter.deleteDoneTasks()
        }


    }
}