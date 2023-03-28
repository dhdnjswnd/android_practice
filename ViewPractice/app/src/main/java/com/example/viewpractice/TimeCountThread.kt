package com.example.viewpractice

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.TextView
import android.widget.Toast

class TimeCountThread(val handler: Handler, val context : Context, val tv : TextView) : Thread() {
    private var time = 0

    override fun run() {
        while(true) {

            // 메세지 객체 전달.
            val msg = handler.obtainMessage()
            val bundle = Bundle()
            bundle.putInt("key", time)
            msg.data = bundle
            handler.sendMessage(msg)

            // post로 runnable 실행
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(context, "시작 Tread : ${this.name}", Toast.LENGTH_SHORT).show()
                // 다른 thread에서 UI에 접근하려하면 ANR발생한다.(Application Not Response
                tv.text = "$time 초"
                time += 5
            }
            sleep(5000)
        }
    }
    fun stopThread(){
        Handler(Looper.getMainLooper()).post{
            Toast.makeText(context, "종료 Tread : ${this.name}", Toast.LENGTH_SHORT).show()
            tv.text = "준비중"
        }
    }
}