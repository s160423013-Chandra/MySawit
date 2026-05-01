package com.example.mysawit.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mysawit.model.Habit
import com.example.mysawit.util.FileHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DashboardViewModel(application: Application) : AndroidViewModel(application) {

    private val habitListData = MutableLiveData<MutableList<Habit>>()
    val habitList: LiveData<MutableList<Habit>> = habitListData

    init {
        loadData()
    }

    private fun loadData() {
        val fileHelper = FileHelper(getApplication())
        val jsonString = fileHelper.readFromFile()

        if (jsonString.isNotEmpty()) {
            try {
                val sType = object : TypeToken<MutableList<Habit>>() {}.type
                val result = Gson().fromJson<MutableList<Habit>>(jsonString, sType)
                habitListData.value = result
                Log.d("MySawit", "Data loaded from file: ${result.size} habits")
            } catch (e: Exception) {
                e.printStackTrace()
                habitListData.value = getDefaultHabits()
            }
        } else {
            habitListData.value = getDefaultHabits()
            saveData()
        }
    }

    private fun getDefaultHabits(): MutableList<Habit> {
        return mutableListOf(
            Habit("1", "Drink Water", "Stay hydrated", 8, 3, "glasses", "Water"),
            Habit("2", "Exercise", "Workout daily", 10, 2, "reps", "Running")
        )
    }

    private fun saveData() {
        val fileHelper = FileHelper(getApplication())
        val jsonString = Gson().toJson(habitListData.value)
        fileHelper.writeToFile(jsonString)
        Log.d("MySawit", "Data saved to: ${fileHelper.getFilePath()}")
    }

    fun addHabit(habit: Habit) {
        val list = habitListData.value ?: mutableListOf()
        list.add(habit)
        habitListData.value = list
        saveData()
    }

    fun increaseProgress(position: Int) {
        val list = habitListData.value
        list?.get(position)?.let {
            if (it.progress < it.goal) it.progress++
        }
        habitListData.value = list
        saveData()
    }

    fun decreaseProgress(position: Int) {
        val list = habitListData.value
        list?.get(position)?.let {
            if (it.progress > 0) it.progress--
        }
        habitListData.value = list
        saveData()
    }
}