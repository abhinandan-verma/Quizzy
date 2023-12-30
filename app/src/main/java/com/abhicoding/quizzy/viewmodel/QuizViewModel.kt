package com.abhicoding.quizzy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.abhicoding.quizzy.model.QuestionsList
import com.abhicoding.quizzy.repository.Repository

class QuizViewModel : ViewModel() {
    private var repository = Repository()
    private var questionsLiveData : LiveData<QuestionsList>
            = repository.getQuestionFromAPI()
    fun getQuestionsFromLiveData():LiveData<QuestionsList>{
        return repository.getQuestionFromAPI()
    }
}