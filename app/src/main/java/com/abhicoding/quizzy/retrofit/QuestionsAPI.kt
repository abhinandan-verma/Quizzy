package com.abhicoding.quizzy.retrofit

import com.abhicoding.quizzy.model.QuestionsList
import retrofit2.Response
import retrofit2.http.GET

interface QuestionsAPI {
    @GET("questionsapi.php")
    suspend fun getQuestions(): Response<QuestionsList>
}