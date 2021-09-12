package com.key2iqintern.key2iqquizapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.key2iqintern.key2iqquizapp.activity.MainActivity
import com.key2iqintern.key2iqquizapp.adapter.QuizAdapter
import com.key2iqintern.key2iqquizapp.databinding.FragmentQuizBinding
import com.key2iqintern.key2iqquizapp.model.QuizModel

class QuizFragment : Fragment() {

    companion object {
        lateinit var binding: FragmentQuizBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                NavHostFragment.findNavController(this@QuizFragment).navigateUp()
                MainActivity.binding.bottomNavigation.visibility = View.VISIBLE
                MainActivity.binding.appBar.visibility = View.VISIBLE
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizBinding.inflate(inflater, container, false)

        val adapter = QuizAdapter()
        adapter.submitList(makeQuiz())

        binding.apply {
            viewPager.offscreenPageLimit = 3
            viewPager.clipToPadding = false
            viewPager.clipChildren = false
            viewPager.adapter = adapter
        }

        binding.quizAppBar.btBack.setOnClickListener {
            findNavController().navigateUp()
            MainActivity.binding.bottomNavigation.visibility = View.VISIBLE
            MainActivity.binding.appBar.visibility = View.VISIBLE
        }

        return binding.root
    }

    private fun makeQuiz(): List<QuizModel> {
        val quizList = mutableListOf<QuizModel>()
        quizList.clear()
        val options1 = mapOf(
            "Sealed Classes" to false,
            "Lambda Functions" to false,
            "The Kotlin Extension" to false,
            "Elvis Operator" to true
        )
        val quizQuestion1 = QuizModel(0, "What handles null exceptions in kotlin?", options1)

        val options2 = mapOf(
            "YES" to false,
            "NO" to true
        )
        val quizQuestion2 = QuizModel(1, "Does Kotlin use the 'static' keyword?", options2)
        val options3 = mapOf(
            "It will react on broadcast announcements" to true,
            "It will do background functionalities as services" to false,
            "It will pass the data between activities" to false,
            "None of the above" to false
        )
        val quizQuestion3 = QuizModel(3, "What is broadcast receiver in android?", options3)
        quizList.add(quizQuestion1)
        quizList.add(quizQuestion2)
        quizList.add(quizQuestion3)

        return quizList
    }

}