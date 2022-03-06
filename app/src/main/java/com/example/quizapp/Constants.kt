package com.example.quizapp

object Constants {

    const val USER_NAME:String="user_name"
    const val TOTAL_QUESTIONS:String="total_questions"
    const val CORRECT_ANSWERS:String="correct_answers"

    fun getQuestions():ArrayList<Question>{
        val questionList=ArrayList<Question>()
        val que1=Question(1,"What country does this flag belong to?",R.drawable.flag_of_argentina,
        "Argentina","Australia","Armenia","Austria",1)
        questionList.add(que1)
        return questionList
    }

}