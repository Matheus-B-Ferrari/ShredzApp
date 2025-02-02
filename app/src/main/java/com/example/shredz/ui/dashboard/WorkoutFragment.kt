package com.example.shredz.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shredz.databinding.FragmentWorkoutBinding
import java.util.Calendar
import com.example.shredz.R
import android.widget.Button

class WorkoutFragment : Fragment() {

    private var _binding: FragmentWorkoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val workoutViewModel =
            ViewModelProvider(this).get(WorkoutViewModel::class.java)

        _binding = FragmentWorkoutBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Remove or comment out these lines
        // val textView: TextView = binding.textDashboard
        // workoutViewModel.text.observe(viewLifecycleOwner) {
        //     textView.text = it
        // }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getGreeting(): Int {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)

        return when {
            hour < 12 -> R.string.good_morning
            hour < 18 -> R.string.good_afternoon
            else -> R.string.good_evening
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the user's name (for now, using a hardcoded value from strings.xml)
        val userName = getString(R.string.user_name)

        // Set the greeting text using the string resource and placeholder
        binding.greetingText.text = getString(getGreeting(), userName)

        binding.startRoutineButton.setOnClickListener {
            // Navigate to the routine screen or start the routine
        }
    }
}
