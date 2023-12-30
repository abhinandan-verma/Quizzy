package com.abhicoding.quizzy.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abhicoding.quizzy.model.QuestionsList
import com.abhicoding.quizzy.retrofit.QuestionsAPI
import com.abhicoding.quizzy.retrofit.RetrofitInstance
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Repository {
    private var questionsAPI:QuestionsAPI = RetrofitInstance()
                    .getRetrofitInstance().create(QuestionsAPI::class.java)

    @OptIn(DelicateCoroutinesApi::class)
     fun getQuestionFromAPI(): LiveData<QuestionsList>{
       // LiveData
        val data = MutableLiveData<QuestionsList>()
        var questionsList : QuestionsList

        GlobalScope.launch(Dispatchers.IO) {
            // returning the Response<QuestionList>
            val response = questionsAPI.getQuestions()
            // saving the data to the list
            questionsList = response.body()!!
            data.postValue(questionsList)
            Log.i("TAG",""+data.value)
        }
        return data
    }
}