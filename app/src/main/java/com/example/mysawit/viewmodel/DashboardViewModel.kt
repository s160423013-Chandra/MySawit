package com.example.mysawit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mysawit.model.Habit

class DashboardViewModel : ViewModel() {

    private val habitListData = MutableLiveData<MutableList<Habit>>()
    val habitList: LiveData<MutableList<Habit>> = habitListData

    init {
        loadData()
    }

    private fun loadData() {
        habitListData.value = mutableListOf(
            Habit("Drink Water", "Stay hydrated", 8, 3, "glasses"),
            Habit("Exercise", "Workout daily", 10, 2, "minutes")
        )
    }

    fun increaseProgress(position: Int) {
        val list = habitListData.value
        list?.get(position)?.let {
            if (it.progress < it.goal) it.progress++
        }
        habitListData.value = list
    }

    fun decreaseProgress(position: Int) {
        val list = habitListData.value
        list?.get(position)?.let {
            if (it.progress > 0) it.progress--
        }
        habitListData.value = list
    }
}