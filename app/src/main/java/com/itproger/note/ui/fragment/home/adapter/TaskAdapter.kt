package com.itproger.note.ui.fragment.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itproger.note.databinding.ItemTaskBinding
import com.itproger.note.model.Task

class TaskAdapter(
    private val click: Result, private val update: Update
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private val list = arrayListOf<Task>()

    @SuppressLint("NotifyDataSetChanged")
    fun setTasks(tasks: List<Task>) {
        list.clear()
        list.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TaskViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(list[position])
        holder.binding.btnDelete.setOnClickListener {
            click.onClick(list[position])
        }
        holder.binding.btnEdit.setOnClickListener {
            update.onUpdate(list[position])
        }
    }

    inner class TaskViewHolder(val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.itemTvTitle.text = task.title
            binding.itemTvDescription.text = task.description
        }
    }

    interface Result {
        fun onClick(position: Task)
    }

    interface Update {
        fun onUpdate(position: Task)
    }
}
