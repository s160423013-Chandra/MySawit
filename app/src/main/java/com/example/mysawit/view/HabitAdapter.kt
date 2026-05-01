package com.example.mysawit.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mysawit.databinding.ItemHabitBinding
import com.example.mysawit.model.Habit
import com.example.mysawit.R
import com.example.mysawit.viewmodel.DashboardViewModel

class HabitAdapter(
    private var list: MutableList<Habit>,
    private val viewModel: DashboardViewModel
) : RecyclerView.Adapter<HabitAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemHabitBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHabitBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val habit = list[position]
        val iconRes = when (habit.iconName) {
            "Running"  -> R.drawable.ic_running
            "Water"    -> R.drawable.ic_water
            "Sleep"    -> R.drawable.ic_sleep
            "Food"     -> R.drawable.ic_food
            "Book"     -> R.drawable.ic_book
            else       -> R.drawable.ic_launcher_foreground
        }
        holder.binding.imgIcon.setImageResource(iconRes)

        holder.binding.txtName.text = habit.name
        holder.binding.txtDesc.text = habit.desc
        holder.binding.progressBar.max = habit.goal
        holder.binding.progressBar.progress = habit.progress

        holder.binding.txtStatus.text =
            if (habit.progress >= habit.goal) "Completed"
            else "In Progress"

        holder.binding.btnPlus.setOnClickListener {
            viewModel.increaseProgress(position)
        }

        holder.binding.btnMinus.setOnClickListener {
            viewModel.decreaseProgress(position)
        }
        holder.binding.txtProgress.text = "${habit.progress} / ${habit.goal} ${habit.unit}"
    }

    override fun getItemCount(): Int = list.size
    fun updateData(newList: MutableList<Habit>) {
        list = newList
        notifyDataSetChanged()
    }

}