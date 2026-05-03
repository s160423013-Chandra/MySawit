package com.example.mysawit.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mysawit.databinding.FragmentNewHabitBinding
import com.example.mysawit.model.Habit
import com.example.mysawit.viewmodel.DashboardViewModel

class NewHabitFragment : Fragment() {
    private lateinit var binding: FragmentNewHabitBinding
    private lateinit var viewModel: DashboardViewModel

    private val iconOptions = arrayOf("Running", "Water", "Sleep", "Food", "Book")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        viewModel = ViewModelProvider(requireActivity())[DashboardViewModel::class.java]

        val iconAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            iconOptions
        )
        binding.spinnerIcon.setAdapter(iconAdapter)

        binding.btnCreateHabit.setOnClickListener {
            val name = binding.etHabitName.text.toString().trim()
            val desc = binding.etDescription.text.toString().trim()
            val goalStr = binding.etGoal.text.toString().trim()
            val unit = binding.etUnit.text.toString().trim()
            val icon = binding.spinnerIcon.text.toString()

            if (name.isEmpty() || goalStr.isEmpty()) {
                return@setOnClickListener
            }

            val goal = goalStr.toIntOrNull() ?: 1

            val newHabit = Habit(
                id = System.currentTimeMillis().toString(),
                name = name,
                desc = desc,
                goal = goal,
                progress = 0,
                unit = unit.ifEmpty { "times" },
                iconName = icon.ifEmpty { "Running" }
            )

            viewModel.addHabit(newHabit)

            findNavController().popBackStack()
        }
    }
}