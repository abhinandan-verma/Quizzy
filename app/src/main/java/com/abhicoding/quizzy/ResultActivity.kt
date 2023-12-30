package com.abhicoding.quizzy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.abhicoding.quizzy.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this,R.layout.activity_result)

        binding.score.text = ""+MainActivity.result+"/"+MainActivity.totalQuestions
        binding.backButton.setOnClickListener {
            val intent = Intent(this@ResultActivity, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this@ResultActivity,"Quiz Completed Successfully",Toast.LENGTH_SHORT).show()
        }
    }
}