package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mUsername:String?=null
    private var mCurrentPosition=1
    private var mQuestionList:ArrayList<Question>?=null
    private var mSelectedOption=0
    private var mCorrectAnswers=0
    private var pressed=false

    private var pbProgress:ProgressBar?=null
    private var tvProgress:TextView?=null
    private var tvQuestion:TextView?=null
    private var ivFlag:ImageView?=null
    private var tvOptionOne:TextView?=null
    private var tvOptionTwo:TextView?=null
    private var tvOptionThree:TextView?=null
    private var tvOptionFour:TextView?=null
    private var btnSubmit:Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUsername=intent.getStringExtra(Constants.USER_NAME)

        pbProgress=findViewById(R.id.pbProgress)
        tvProgress=findViewById(R.id.tvProgress)
        tvQuestion=findViewById(R.id.tvQuestion)
        ivFlag=findViewById(R.id.ivFlag)
        tvOptionOne=findViewById(R.id.tvOptionOne)
        tvOptionTwo=findViewById(R.id.tvOptionTwo)
        tvOptionThree=findViewById(R.id.tvOptionThree)
        tvOptionFour=findViewById(R.id.tvOptionFour)
        btnSubmit=findViewById(R.id.btnSubmit)

        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        mQuestionList = Constants.getQuestions()

        setQuestion()
    }

    private fun setQuestion() {
        defaultOptionView()

        val question: Question = mQuestionList!![mCurrentPosition - 1]
        ivFlag?.setImageResource(question.image)
        pbProgress?.progress = mCurrentPosition
        tvProgress?.text = "$mCurrentPosition / ${pbProgress?.max}"
        tvQuestion?.text = question.question
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour
        btnSubmit?.text="SUBMIT"
    }

    private fun defaultOptionView(){
        val options=ArrayList<TextView>()
        tvOptionOne?.let { options.add(0,it) }
        tvOptionTwo?.let { options.add(1,it) }
        tvOptionThree?.let { options.add(2,it) }
        tvOptionFour?.let { options.add(3,it) }

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface= Typeface.DEFAULT
            option.background=ContextCompat.getDrawable(this,R.drawable.default_option_border_bg)
        }
    }

    private fun selectedOptionView(tv:TextView, selected:Int){
        defaultOptionView()
        mSelectedOption=selected
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background=ContextCompat.getDrawable(this,R.drawable.selected_option_border_bg)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.tvOptionOne->tvOptionOne?.let { selectedOptionView(it,1) }
            R.id.tvOptionTwo->tvOptionTwo?.let { selectedOptionView(it,2) }
            R.id.tvOptionThree->tvOptionThree?.let { selectedOptionView(it,3) }
            R.id.tvOptionFour->tvOptionFour?.let { selectedOptionView(it,4) }
            R.id.btnSubmit->{
                pressed=true
                if (mSelectedOption==0){
                    mCurrentPosition++

                    if(mCurrentPosition<=mQuestionList!!.size){
                        setQuestion()

                    }else{
                        val intent=Intent(this,ResultActivity::class.java)
                        intent.putExtra(Constants.USER_NAME, mUsername)
                        intent.putExtra(Constants.CORRECT_ANSWERS,mCorrectAnswers)
                        intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionList?.size)
                        startActivity(intent)
                        finish()
                    }

                }else{
                    val question=mQuestionList?.get(mCurrentPosition-1)
                    if(question!!.correctAnswer!=mSelectedOption){
                        answerView(mSelectedOption,R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer,R.drawable.correct_option_border_bg)

                    if (mCurrentPosition==mQuestionList!!.size&&pressed){
                        btnSubmit?.text="FINISH"
                    }else{
                        btnSubmit?.text="NEXT QUESTION"
                    }

                    mSelectedOption=0
                }
            }
        }
    }

    private fun answerView(answer:Int, drawableView:Int){
        when(answer){
            1->tvOptionOne?.background=ContextCompat.getDrawable(this,drawableView)
            2->tvOptionTwo?.background=ContextCompat.getDrawable(this,drawableView)
            1->tvOptionThree?.background=ContextCompat.getDrawable(this,drawableView)
            1->tvOptionFour?.background=ContextCompat.getDrawable(this,drawableView)
        }
    }

}