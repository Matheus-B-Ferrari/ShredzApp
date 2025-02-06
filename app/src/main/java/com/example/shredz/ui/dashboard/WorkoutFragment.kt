package com.example.shredz.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shredz.databinding.FragmentWorkoutBinding
import java.util.Calendar
import com.example.shredz.R
import android.widget.TextView

import com.example.shredz.model.User
import com.example.shredz.model.Exercicio
import com.example.shredz.model.Treinamento
import com.example.shredz.model.Rotina

import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import java.text.SimpleDateFormat
import java.util.*

class WorkoutFragment : Fragment() {

    private var _binding: FragmentWorkoutBinding? = null
    private val binding get() = _binding!!

    // Declare the user property here
    private lateinit var user: User

    private fun getGreeting(): Int {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)

        return when (hour) {
            in 5..11 -> R.string.good_morning
            in 12..17 -> R.string.good_afternoon
            else -> R.string.good_evening
        }
    }

    private fun getTodaysRoutine(): Rotina? {
        val today = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        return user.rotinas.find { it.dayOfWeek == today }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkoutBinding.inflate(inflater, container, false)
        setCurrentDate()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the user with sample data
        user = User(nome = "Matheus", peso = 75f)

        // Example exercises and routines
        val exercicio1 = Exercicio(nome = "Supino")
        val exercicio2 = Exercicio(nome = "Agachamento")
        val treinamento = Treinamento(nome = "Treino de Peito", exercicios = mutableListOf(exercicio1, exercicio2))
        user.treinamentos.add(treinamento)

        // Define routines
        val routine1 = Rotina(
            nome = "Rotina de Hipertrofia",
            exercicios = mutableListOf(exercicio1, exercicio2),
            dayOfWeek = Calendar.TUESDAY // Routine 1 on Tuesday
        )

        val routine2 = Rotina(
            nome = "Rotina de Força",
            exercicios = mutableListOf(exercicio1, exercicio2),
            dayOfWeek = Calendar.THURSDAY // Routine 2 on Thursday
        )

        // Add routines to the user
        user.rotinas.add(routine1)
        user.rotinas.add(routine2)

        // Set greeting text with dynamic username
        binding.greetingText.text = getString(getGreeting(), user.nome)

        // Set up the start routine button
        binding.startRoutineButton.setOnClickListener {
            // Navigate to the routine screen or start the routine
        }

        // Add dynamic calendar dates
        addCalendarDates()

        // Set up RecyclerView for routines
        // Display today's routine
        val todaysRoutine = getTodaysRoutine()
        if (todaysRoutine != null) {
            binding.routineText.text = getString(R.string.today_routine, todaysRoutine.nome)
        } else {
            binding.routineText.text = getString(R.string.today_rest_day)
        }

        // Set up RecyclerView for routines
        val routineAdapter = RoutineAdapter(user.rotinas) { selectedRoutine ->
            // Handle routine item click
            Toast.makeText(context, "Selected Routine: ${selectedRoutine.nome}", Toast.LENGTH_SHORT).show()
        }
        binding.routineList.adapter = routineAdapter
        binding.routineList.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun addCalendarDates() {
        // Access the LinearLayout inside the HorizontalScrollView
        val calendarLayout: LinearLayout = binding.horizontalCalendar.findViewById(R.id.calendar_linear_layout)

        // Get the current date
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd", Locale.getDefault())

        // Add 7 days (today + next 6 days)
        for (i in 0..6) {
            val dateTextView = TextView(requireContext()).apply {
                text = dateFormat.format(calendar.time)
                setPadding(16, 8, 16, 8)
                gravity = Gravity.CENTER
                textSize = 16f
                setBackgroundResource(R.drawable.date_background)
                setTextColor(Color.WHITE)
                setOnClickListener {
                    val selectedDate = dateFormat.format(calendar.time)
                    Toast.makeText(context, "Selected Date: $selectedDate", Toast.LENGTH_SHORT).show()
                }
            }
            calendarLayout.addView(dateTextView)
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }
    }

    private fun setCurrentDate() {
        val currentDate = Calendar.getInstance().time

        val dateFormat = SimpleDateFormat("d 'de' MMM. 'de' yyyy", Locale("pt", "BR"))
        val formattedDate = dateFormat.format(currentDate)

        binding.currentDateText.text = formattedDate
    }

}


