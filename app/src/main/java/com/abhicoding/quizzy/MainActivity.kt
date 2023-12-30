package com.abhicoding.quizzy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.abhicoding.quizzy.databinding.ActivityMainBinding
import com.abhicoding.quizzy.model.QuestionsItem
import com.abhicoding.quizzy.viewmodel.QuizViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var quizViewModel: QuizViewModel
    private lateinit var questionsList: List<QuestionsItem>

    companion object{
        var result = 0
        var totalQuestions = 0
    }
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        // Resetting the scores
        result = 0
        totalQuestions = 0

        // getting the response from the retrofit
        quizViewModel = ViewModelProvider(this)[QuizViewModel::class.java]

        // Display the First Questions
        GlobalScope.launch(Dispatchers.Main) {
            quizViewModel.getQuestionsFromLiveData().observe(this@MainActivity){
                if (it.size > 0){
                    questionsList = it
                    Log.i("TAG","This is first Question: ${questionsList[0]}")

                    binding.apply {
                        textView.text = questionsList[0].question
                        radio1.text = questionsList[0].option1
                        radio2.text = questionsList[0].option2
                        radio3.text = questionsList[0].option3
                        radio4.text = questionsList[0].option4

                    }
                }
            }
        }

        // Adding the functionality to next Button
        var i = 1
        binding.apply {
            nextButton.setOnClickListener {
                val selectedOption = radioGroup.checkedRadioButtonId
                if (selectedOption != -1){
                    val radButton = findViewById<View>(selectedOption) as RadioButton

                    questionsList.let {
                        if (i < it.size){
                            // getting the number of questions
                            totalQuestions = it.size
                            // check if it is correct or not
                            if (radButton.text.toString() == it[i-1].correct_option){
                                result++
                                textResult.text = "Correct Answer : $result"
                            }
                            // Displaying the next question
                            textView.text = "Question ${i+1}: "+it[i].question
                            radio1.text = it[i].option1
                            radio2.text = it[i].option2
                            radio3.text = it[i].option3
                            radio4.text = it[i].option4

                            // Checking if the last question
                            if (i == it.size.minus(1)){
                                nextButton.text = "FINISH"
                            }
                            radioGroup.clearCheck()
                            i++
                        }else{
                            if (radButton.text.toString() == it[i-1].correct_option){
                                textResult.text = "Correct Answer : $result"
                                result++
                            }else{

                            }
                            val intent = Intent(this@MainActivity,ResultActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }else{
                    Toast.makeText(this@MainActivity,"Select at least one Option",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}