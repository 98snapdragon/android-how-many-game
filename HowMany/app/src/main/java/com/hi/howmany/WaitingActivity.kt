package com.hi.howmany

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_wait.*
import java.util.*

class WaitingActivity : AppCompatActivity() {

    private var mCounter:Int = 0
    val mTimer = Timer()
    var mTimerTask = makeTimeSchedule()
    var second :String = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wait)

        mTimer.schedule(mTimerTask,0,100)
    }

    private fun makeTimeSchedule() : TimerTask {
        val timerTask: TimerTask = object : TimerTask() {

            override fun run() {
                mCounter++

                if (mCounter == 30) {
                    mTimer.cancel()
                    finish()
                }else if(mCounter == 20){
                    second="1"
                    runOnUiThread { str_seconds.setText(second) }
                }else if(mCounter == 10) {
                    second="2"
                    runOnUiThread { str_seconds.setText(second) }
                }else if(mCounter == 25){
                    second="GO"
                    runOnUiThread { str_seconds.setText(second) }
                }
            }
        }
        return timerTask
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}