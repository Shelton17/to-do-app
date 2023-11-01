package com.example.to_do_app

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do_app.databinding.ItemTaskBinding

class TaskAdapter (
    private val tasks: MutableList<Task>
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(){

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view)


    private lateinit var binding: ItemTaskBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        binding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        val view = binding.root
        return TaskViewHolder(view)

    }



    private fun toggle_strike_through(tv_task_title: TextView, isChecked: Boolean){
        if (isChecked){
            tv_task_title.paintFlags = tv_task_title.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else{
            tv_task_title.paintFlags = tv_task_title.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
    
    fun addTask(task: Task){
        tasks.add(task)
        notifyItemInserted(tasks.size - 1)
    }
    
    fun deleteDoneTasks(){
        tasks.removeAll { task ->
            task.isChecked
        }
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val current_task = tasks[position]
        binding.apply {
            tvTaskTitle.text = current_task.title
            chDone.isChecked = current_task.isChecked
            toggle_strike_through(tvTaskTitle, current_task.isChecked)
            chDone.setOnCheckedChangeListener { _, isChecked ->
                toggle_strike_through(tvTaskTitle, isChecked)
                current_task.isChecked = !current_task.isChecked
            }
        }

    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}