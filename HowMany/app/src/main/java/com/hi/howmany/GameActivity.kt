package com.hi.howmany

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.gridlayout.widget.GridLayout
import kotlinx.android.synthetic.main.activity_game.*
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    private var mAnswer:Int = 0
    private var mCounter:Int = 0
    private var mRightChoice:Int = 0
    private var mStage = 0

    private var mQuestionList:MutableList<MutableList<Int>> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_game)

        mStage=0

        val intent = Intent(this, ResultActivity::class.java)
        mQuestionList.clear()
        mAnswer=0
        mCounter=0
    }

    private fun mMakeQuestionBoard(level:Int, questionBoard: GridLayout?){

        questionBoard?.columnCount=level
        questionBoard?.rowCount=level

        for(i in 1..level*level){
            val color : Int = Random.nextInt(0,4)
            val num : Int = Random.nextInt(1,5)
            val colorNumberSet:MutableList<Int> = ArrayList()

            //Log.d("debug","random color is: "+color)
            //Log.d("debug","random number is: "+num)

            colorNumberSet.add(color)
            colorNumberSet.add(num)

            mQuestionList.add(colorNumberSet)
        }

        for(i in mQuestionList){
            val length = questionBoard!!.layoutParams.width
            val paramQuestion = GridLayout.LayoutParams(ViewGroup.LayoutParams(length/level, length/level))
            val img = ImageView(this)

            if(i.get(0) == 0 && i.get(1)==1){ img.setImageResource(R.drawable.r1) }
            else if(i.get(0) == 0 && i.get(1)==2){ img.setImageResource(R.drawable.r2) }
            else if(i.get(0) == 0 && i.get(1)==3){ img.setImageResource(R.drawable.r3) }
            else if(i.get(0) == 0 && i.get(1)==4){ img.setImageResource(R.drawable.r4) }
            else if(i.get(0) == 1 && i.get(1)==1){ img.setImageResource(R.drawable.g1) }
            else if(i.get(0) == 1 && i.get(1)==2){ img.setImageResource(R.drawable.g2) }
            else if(i.get(0) == 1 && i.get(1)==3){ img.setImageResource(R.drawable.g3) }
            else if(i.get(0) == 1 && i.get(1)==4){ img.setImageResource(R.drawable.g4) }
            else if(i.get(0) == 2 && i.get(1)==1){ img.setImageResource(R.drawable.b1) }
            else if(i.get(0) == 2 && i.get(1)==2){ img.setImageResource(R.drawable.b2) }
            else if(i.get(0) == 2 && i.get(1)==3){ img.setImageResource(R.drawable.b3) }
            else if(i.get(0) == 2 && i.get(1)==4){ img.setImageResource(R.drawable.b4) }
            else if(i.get(0) == 3 && i.get(1)==1){ img.setImageResource(R.drawable.o1) }
            else if(i.get(0) == 3 && i.get(1)==2){ img.setImageResource(R.drawable.o2) }
            else if(i.get(0) == 3 && i.get(1)==3){ img.setImageResource(R.drawable.o3) }
            else if(i.get(0) == 3 && i.get(1)==4){ img.setImageResource(R.drawable.o4) }
            else{ Log.d("debug","out of bound") }

            img.layoutParams = paramQuestion
            questionBoard?.addView(img)
        }

        val target: Int = Random.nextInt(0,level*level)
        val targetColor : Int = mQuestionList.get(target).get(0)
        val targetNum : Int = mQuestionList.get(target).get(1)

        if(targetColor==0 && targetNum==1){ img_target.setImageResource(R.drawable.r1) }
        else if(targetColor==0 && targetNum==2){ img_target.setImageResource(R.drawable.r2) }
        else if(targetColor==0 && targetNum==3){ img_target.setImageResource(R.drawable.r3) }
        else if(targetColor==0 && targetNum==4){ img_target.setImageResource(R.drawable.r4) }
        else if(targetColor==1 && targetNum==1){ img_target.setImageResource(R.drawable.g1) }
        else if(targetColor==1 && targetNum==2){ img_target.setImageResource(R.drawable.g2) }
        else if(targetColor==1 && targetNum==3){ img_target.setImageResource(R.drawable.g3) }
        else if(targetColor==1 && targetNum==4){ img_target.setImageResource(R.drawable.g4) }
        else if(targetColor==2 && targetNum==1){ img_target.setImageResource(R.drawable.b1) }
        else if(targetColor==2 && targetNum==2){ img_target.setImageResource(R.drawable.b2) }
        else if(targetColor==2 && targetNum==3){ img_target.setImageResource(R.drawable.b3) }
        else if(targetColor==2 && targetNum==4){ img_target.setImageResource(R.drawable.b4) }
        else if(targetColor==3 && targetNum==1){ img_target.setImageResource(R.drawable.o1) }
        else if(targetColor==3 && targetNum==2){ img_target.setImageResource(R.drawable.o2) }
        else if(targetColor==3 && targetNum==3){ img_target.setImageResource(R.drawable.o3) }
        else if(targetColor==3 && targetNum==4){ img_target.setImageResource(R.drawable.o4) }
        else {
            Log.d("debug","Target number out of bound")}

        for(k in mQuestionList){
            if(k.get(0)==targetColor&&k.get(1)==targetNum){ mAnswer+=1}
        }

        val fakeOne:Int = Random.nextInt(0,mAnswer)
        val fakeTwo:Int = Random.nextInt(mAnswer+1,(mAnswer+1)*2)
        val rightChoice:Int = Random.nextInt(0,3)

        if(rightChoice==0){
            mRightChoice=rightChoice
            btn_selection_1.setText(mAnswer.toString())
            btn_selection_2.setText(fakeOne.toString())
            btn_selection_3.setText(fakeTwo.toString())
        }else if(rightChoice==1){
            mRightChoice=rightChoice
            btn_selection_2.setText(mAnswer.toString())
            btn_selection_1.setText(fakeOne.toString())
            btn_selection_3.setText(fakeTwo.toString())
        }else if(rightChoice==2){
            mRightChoice=rightChoice
            btn_selection_3.setText(mAnswer.toString())
            btn_selection_1.setText(fakeOne.toString())
            btn_selection_2.setText(fakeTwo.toString())
        }

        Log.d("debug","The answer is :" + mAnswer)
        Log.d("debug","QuestionList"+mQuestionList.toString())
    }
}