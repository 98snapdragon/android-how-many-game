package com.hi.howmany

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val intent_return = Intent()

        val intent_given = getIntent()

        val result : Int = intent_given.getIntExtra("result",3)

        if(result==0){
            btn_next_step.setOnClickListener {
                intent_return.putExtra("return",0)
                setResult(Activity.RESULT_OK,intent_return)
                finish()
            }
        }else if (result==1){
            img_result.setImageResource(R.drawable.wrong)
            btn_next_step.setText("Go to Main")
            btn_next_step.setOnClickListener {
                intent_return.putExtra("return",1)
                finish()
            }
        }else{
            img_result.setImageResource(R.drawable.times_up)
            btn_next_step.setText("Go to Main")
            btn_next_step.setOnClickListener {
                intent_return.putExtra("return",2)
                finish()
            }
        }
    }
}